package co.com.prueba.ibm.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringApplicationContextContainer implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Autowired
    public SpringApplicationContextContainer(ApplicationContext applicationContext) {
    	System.err.println("Configuración inicial!!!");
    	System.err.println("Configuración aplicación *** "+applicationContext.getApplicationName());
    	SpringApplicationContextContainer.applicationContext = applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationContextContainer.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return SpringApplicationContextContainer.applicationContext;
    }

}
