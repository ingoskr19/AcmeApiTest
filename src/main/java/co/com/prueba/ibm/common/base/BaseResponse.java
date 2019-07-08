package co.com.prueba.ibm.common.base;

import java.io.Serializable;

public class BaseResponse<B extends Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean ok = true;
	private String msgError;
	private int errorCode = 0;
	private B model;

	public BaseResponse() {
	}

	public B getModel() {
		return model;
	}

	public void setModel(B model) {
		this.model = model;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
