package co.com.prueba.ibm.common.base;

public class FileModel extends BaseModel {

	private static final long serialVersionUID = 1L;
	private String base64;
	private String contentType;
	
	public FileModel(){
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}

