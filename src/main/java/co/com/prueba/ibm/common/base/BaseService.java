package co.com.prueba.ibm.common.base;

import java.lang.reflect.ParameterizedType;

public class BaseService <B extends BaseRepository> {

	protected B repository;

	@SuppressWarnings("unchecked")
	public BaseService(){
		setupRepository(
			(Class<B>) (((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0])
        );
	}

    public B getRepository() {
        return repository;
    }

    public void setRepository( B repository ) {
        this.repository = repository;
    }

    protected void setupRepository( Class<B> classRepository ) {
    	if( repository == null ){
    		synchronized ( BaseController.class ) {
    			if( repository == null ){
    	            try {
    	                setRepository( classRepository.newInstance() );
    	            } catch (Exception e) {
    	                e.printStackTrace();
    	            }
    			}
			}
    	}
    }

}
