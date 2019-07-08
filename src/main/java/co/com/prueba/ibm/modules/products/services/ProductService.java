package co.com.prueba.ibm.modules.products.services;

import java.util.List;

import org.springframework.stereotype.Component;

import co.com.prueba.ibm.common.base.BaseSpringService;
import co.com.prueba.ibm.modules.products.model.Product;
import co.com.prueba.ibm.modules.products.repository.ProductRepository;

@Component
public class ProductService extends BaseSpringService<ProductRepository> {
	
	public Product getProductById(int ProductId) {
		Product obj = getRepository().getProductById(ProductId);
		return obj;
	}	
	
	public List<Product> getAllProducts(){
		return getRepository().getAllProducts();
	}
	
	public synchronized boolean addProduct(Product Product){
	    getRepository().addProduct(Product);
	    return true;
	}
	
	public void updateProduct(Product Product) {
		getRepository().updateProduct(Product);
	}
	
	public void deleteProduct(int ProductId) {
		getRepository().deleteProduct(ProductId);
	}
} 