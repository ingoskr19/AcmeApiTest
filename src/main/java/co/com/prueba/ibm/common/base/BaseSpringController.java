package co.com.prueba.ibm.common.base;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import co.com.prueba.ibm.spring.SpringApplicationContextContainer;

public class BaseSpringController <B extends BaseService<?>> extends BaseController<B> {

	@Autowired
	SmartValidator validator;
    
	@Override
	protected void setupService( Class<B> classService ) {
		if( !classService.getName().equals("co.com.prueba.ibm.common.base.BaseService") && !classService.getName().equals("co.com.prueba.ibm.common.base.BaseSpringService") ) {
			if(SpringApplicationContextContainer.getApplicationContext() != null) {
				setService( SpringApplicationContextContainer.getApplicationContext().getBean( classService ) );
			} else {
				super.setupService(classService);
			}
		}
    }

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<BaseResponse<?>> handleException(Exception ex, WebRequest request) {
		ResponseEntity<BaseResponse<?>> response = null;
		BaseResponse<?> obj = new BaseResponse<Object>();
		obj.setOk(false);
		
		if(ex instanceof SQLException || ex instanceof UncategorizedSQLException) {
			obj.setMsgError("Ha ocurrido un error interno.");
			obj.setErrorCode(20000);
			response = new ResponseEntity<BaseResponse<?>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			obj.setMsgError((ex.getCause() != null) ? ex.getCause().getMessage():"");
			obj.setErrorCode(1);
			response = new ResponseEntity<BaseResponse<?>>(obj, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		ex.printStackTrace();
	    return response;
	}

}

