package co.com.prueba.ibm.modules.products.model;

import java.util.Date;

public class Product {
	
	private long productId;
	private String desProduct;
	private int productState;
	private String productInitials;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getDesProduct() {
		return desProduct;
	}
	public void setDesProduct(String desProduct) {
		this.desProduct = desProduct;
	}
	public int getProductState() {
		return productState;
	}
	public void setProductState(int productState) {
		this.productState = productState;
	}
	public String getProductInitials() {
		return productInitials;
	}
	public void setProductInitials(String productInitials) {
		this.productInitials = productInitials;
	}
}
