package co.com.prueba.ibm.modules.products.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import co.com.prueba.ibm.common.base.BaseSpringController;
import co.com.prueba.ibm.modules.products.model.Product;
import co.com.prueba.ibm.modules.products.services.ProductService;

@RestController
@RequestMapping(path = "/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductsController extends BaseSpringController<ProductService> {

	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public ResponseEntity<Void> addProduct(@RequestBody Product Product, UriComponentsBuilder builder) {
		boolean flag = getService().addProduct(Product);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/Product/{id}").buildAndExpand(Product.getProductId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public ResponseEntity<Product> updateProduct(@RequestBody Product Product) {
		getService().updateProduct(Product);
		return new ResponseEntity<Product>(Product, HttpStatus.OK);
	}

	@RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
		getService().deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = { "/get/{id}" }, method = RequestMethod.POST)
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
		Product Product = getService().getProductById(id);
		return new ResponseEntity<Product>(Product, HttpStatus.OK);
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> list = getService().getAllProducts();
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
}