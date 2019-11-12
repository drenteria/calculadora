# Prueba Técnica Technical Product Architect Cyxtera
*2019-11-12*

*Daniel Felipe Renteria Martinez*

# Descripcion
---
La aplicacion web para calculos expone servicios REST que permiten ejecutar operaciones matemáticas basicas (suma, resta multiplicación, división y potenciación). Cada calculadora tiene un identificador de sesion, con el que el usuario podra añadir operadores y ejecutar operaciones mientras el objeto calculadora se mantenga en la sesión.

Cabe anotar que cuando se ejecuta una operacion, la calculadora automáticamente vacía su lista de operandos anterior y deja únicamente el resultado de la operación solicitada siempre y cuando esta sea valida. Esto permite que la sesion pueda seguir siendo usada y que el resultado de la operacion inmediatamente anterior haga parte de los nuevos operandos.

Esta aplicación esta creada en lenguaje de programacion Java y el ciclo de desarrollo esta en Maven. Las pruebas se realizaron en el contenedor de aplicaciones Apache Tomcat 8.5, pero al ser una aplicación web, puede ser desplegada en cualquier servidor de aplicaciones JEE (JBoss, WildFly, Jetty, etc.)

## Operaciones implementadas
---
* `iniciarSesion` Inicia una nueva sesion de calculadora y retorna el identificador UUID de la sesion.
* `adicionar` Permite adicionar operadores numericos a la sesion actual de la calculadora
* `ejecutar` Permite ejecutar con la lista actual de operadores un calculo dentro de los mencionados anteriormente
* `finalizar` Finaliza una sesion de calculadora

### Pruebas Unitarias a clases Core
---
Se crearon pruebas unitarias JUnit para las clases que ejecutan las operaciones matemáticas dentro de la calculadora, así como a la calculadora misma. Para ejecutar las pruebas de las clases core se requiere unicamente la ejecucion de los test desde maven con el comando

	mvn clean compile test
 	
### Log de Aplicación
---
El log de la aplicación se puede encontrar en el directorio temporal del usuario que este ejecutando la aplicación, ya que los logs se encuentran configurados en la variable de entorno de Java `java.io.tmpdir`. El log se llama Calculadora.java y se encuentra configurado para renovarse diariamente. La configuración de este log puede encontrarse en el archivo `src/main/resources/log4j2.xml`

## Pruebas a Servicios Rest
---
Los servicios REST que provee la aplicacion pueden ser probados a traves de un cliente REST, como Postman. Si se cuenta con esta aplicación, puede utilizar el archivo `src/main/resources/CyxteraCalculadora.postman_collection.json` para importar en postman una colección con los request de prueba para cada una de las cuatro operaciones.

#### Metodo iniciarSesion

*URL*

<http://servidor:puerto/calculadora/api/iniciarSesion>

*Verbo HTTP*

GET

*Request*

Este request no admite payload

*Response*

Se obtiene un objeto Json con los atributos de la calculadora actual, principalmente el id de sesion:

    {
	    "idSesion": "884f2751-c493-4f62-b79c-fbdecd9c598d",
	    "listaOperandos": []
	}

#### Metodo adicionar

*URL*

<http://servidor:puerto/calculadora/api/adicionar>

*Verbo HTTP*

POST

*Request*

    {
    	"idSesion": "884f2751-c493-4f62-b79c-fbdecd9c598d",
    	"valor": "2"
    }

*Response*

Se obtiene un objeto Json con los atributos de la calculadora actual, de tal manera que la lista de operandos aparece llena con el último:

	{
	    "idSesion": "884f2751-c493-4f62-b79c-fbdecd9c598d",
	    "listaOperandos": [
	        3.0,
	        2.0
	    ]
	}

*Nota*: 
- Si el identificacdor de sesion ingresado no existe, la respuesta es de código HTTP 504 (Bad Request).

#### Metodo ejecutar

*URL*

<http://servidor:puerto/calculadora/api/ejecutar>

*Verbo HTTP*

POST

*Request*

    {
    	"idSesion": "884f2751-c493-4f62-b79c-fbdecd9c598d",
    	"operacion":"suma"
    }

*Response*

Se obtiene un objeto Json con los atributos de la calculadora actual, pero en la lista de operandos solo se muestra el resultado de la operacion solicitada:

	{
	    "idSesion": "884f2751-c493-4f62-b79c-fbdecd9c598d",
	    "listaOperandos": [
	        5.0
	    ]
	}

*Nota*: 
- Si la operacion ingresada no es permitida, la respuesta es de código HTTP 504 (Bad Request).
- Si el identificacdor de sesion ingresado no existe, la respuesta es de código HTTP 504 (Bad Request).

#### Metodo finalizar

*URL*

<http://servidor:puerto/calculadora/api/finalizar/{idSesion}>

*Verbo HTTP*

DELETE

*Request*

Este request no contiene payload. El id de sesion que se desea finalizar va como parte de la URL solicitada:

	http://localhost:8080/calculadora/api/finalizar/884f2751-c493-4f62-b79c-fbdecd9c598d

*Response*

Se obtiene una respuesta en texto plano de si se pudo eliminar la sesion `OK` o si la sesion ingresada no existe `No encontrada`