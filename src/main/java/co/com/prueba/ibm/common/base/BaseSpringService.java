package co.com.prueba.ibm.common.base;

import co.com.prueba.ibm.spring.SpringApplicationContextContainer;

public class BaseSpringService <B extends BaseRepository> extends BaseService <B> {
	
    Class<B> pendingClassRepository;
	
	@Override
	protected void setupRepository( Class<B> classRepository ) {
    	if( !classRepository.getName().equals("co.com.prueba.ibm.common.base.BaseRepository") ){
    		if( SpringApplicationContextContainer.getApplicationContext()!=null ){
    			setRepository( SpringApplicationContextContainer.getApplicationContext().getBean( classRepository ) );
    		} else {
    			pendingClassRepository = classRepository;
    		}
    	}
    }
	
	public B getRepository() {
    	if( repository == null && pendingClassRepository!=null ){
    		setRepository( SpringApplicationContextContainer.getApplicationContext().getBean( pendingClassRepository ) );
    		pendingClassRepository = null;
    	}
        return repository;
    }

}
