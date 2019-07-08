package co.com.prueba.ibm.modules.products.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import co.com.prueba.ibm.common.base.GenericRowMapper;
import co.com.prueba.ibm.modules.products.model.Product;

public class ProductRowMapper extends GenericRowMapper implements RowMapper<Product> {
	
	RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
	
	public Product mapRow(ResultSet row, int rowNum) throws SQLException {
		return mapFromRS(row);
	}
	
	//productId,desProduct,productState,productInitials
	public Product mapFromRS(ResultSet row) throws SQLException {
		Product product = new Product();
		if(existsColumn("productId",Types.INTEGER, row))
			product.setProductId(row.getLong("productId"));
		
		if(existsColumn("desProduct",Types.VARCHAR, row))
			product.setDesProduct(row.getString("desProduct"));
				
		if(existsColumn("productState",Types.INTEGER, row))
			product.setProductState(row.getInt("productState"));
		
		if(existsColumn("productInitials",Types.VARCHAR, row))
			product.setProductInitials(row.getString("productInitials"));
		
		return product;
	}
	
} 