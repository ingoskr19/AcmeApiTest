package co.com.prueba.ibm.modules.products.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.com.prueba.ibm.common.base.BaseRepository;
import co.com.prueba.ibm.modules.products.helper.ProductRowMapper;
import co.com.prueba.ibm.modules.products.model.Product;

@Transactional
@Repository
public class ProductRepository extends BaseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*
	 * productId,desProduct,productState,productInitials
	 */
	public void addProduct(Product Product) {
		String sql = "INSERT INTO Products (desProduct,productState,productInitials) values (?,?,?,?,?)";
		jdbcTemplate.update(sql, 
			Product.getProductId(), 
			Product.getDesProduct(), 
			Product.getProductState(),
			Product.getProductInitials()
			);

		sql = "SELECT ProductId FROM Products WHERE desProduct = ? and productInitials = ?";
		Product lastInsert = jdbcTemplate.queryForObject(sql, Product.class, Product.getDesProduct(), Product.getProductInitials());
		Product.setProductId(lastInsert.getProductId());
	}
	
	public void updateProduct(Product product) {
		String sql = "UPDATE Products SET desProduct = ?, productState = ?,productInitials = ? where ProductId = ?";
		jdbcTemplate.update(sql, 
				product.getDesProduct(), 
				product.getProductState(), 
				product.getProductInitials(),
				product.getProductId()
				);
	}
	
	public void deleteProduct(int ProductId) {
		String sql = "DELETE FROM Products WHERE ProductId=?";
		jdbcTemplate.update(sql, ProductId);
	}
	
	public Product getProductById(int ProductId) {
		String sql = "SELECT productId,desProduct,productState,productInitials FROM Products WHERE ProductId = ?";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		Product Product = jdbcTemplate.queryForObject(sql, rowMapper, ProductId);
		return Product;
	}
	
	public List<Product> getAllProducts() {
		String sql = "SELECT productId,desProduct,productState,productInitials FROM Products";
		RowMapper<Product> rowMapper = new ProductRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
}