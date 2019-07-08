package co.com.prueba.ibm.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = { "co.com.prueba.ibm.*"})
public class Application {

    public static void main(String[] args) {
		String appName = System.getProperty("app.name");
		System.out.println("");
    	System.out.println("Iniciando aplicaci�n "+appName);			
		System.out.println("Total memory: "+Runtime.getRuntime().totalMemory());
		System.out.println("Free memory: "+Runtime.getRuntime().freeMemory());
		System.out.println("Max memory: "+Runtime.getRuntime().maxMemory());
		System.out.println("");
		System.setProperty("java.awt.headless", "true");
		/*System.setOut( new DebugLogPrintStream( "stdout" ) );
		System.setErr( new ErrorLogPrintStream( "stderr" ) );*/
    	SpringApplication springApplication = new SpringApplication( Application.class );
    	springApplication.run( args );
        
   }

}