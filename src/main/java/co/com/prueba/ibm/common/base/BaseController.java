package co.com.prueba.ibm.common.base;

import java.lang.reflect.ParameterizedType;

public class BaseController <B extends BaseService<?>> {

	private B service;

	@SuppressWarnings("unchecked")
	public BaseController(){
		Object obj = (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		if( !( obj instanceof ParameterizedType ) ){
			setupService( (Class<B>) obj );			
		}
	}

    public B getService() {
        return service;
    }

    public void setService( B service ) {
        this.service = service;
    }

    protected void setupService( Class<B> classService ) {
    	if( service == null ){
    		synchronized ( BaseController.class ) {
    			if( service == null ){
    	            try {
    	            	setService( classService.newInstance() );
    	            } catch (Exception e) {
    	                e.printStackTrace();
    	            }
    			}
			}
    	}
    }
}