# AcmeApiTest
Implementación prueba API

####PRERREQUISITOS

- Tener instalado node, npm, maven, mysql, git

#BACKEND

1. Clonar el proyecto con: git clone https://github.com/ingoskr19/AcmeApiTest.git;
2. pasarse de rama con: git checkout feature/API_IMPLEMENTACION
2. Dentro de la carpeta AcmeApiTest/ ejecutar install.bat
3. Ejecutar Script database.sql en la base de datos mysql
4. Modificar en el archivo application.properties la siguientes propiedades de acuerdo a su configuración de mysql:
	
	# HTTP Server port
	server.port=26172

	#DataSource Configuration
	spring.datasource.driver-class-name=com.mysql.jdbc.Driver
	spring.datasource.url=jdbc:mysql://localhost:3306/ibmtest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	spring.datasource.username=root
	spring.datasource.password=

5. ejecutar run.bat
