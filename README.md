# Calculadora REST
*2019-11-12*

*Daniel Felipe Renteria Martinez*

# Descripcion
---
La aplicacion web para calculos expone servicios REST que permiten ejecutar operaciones matemáticas basicas (suma, resta multiplicación, división y potenciación). Cada calculadora tiene un identificador de sesion, con el que el usuario podra añadir operadores y ejecutar operaciones mientras el objeto calculadora se mantenga en la sesión.

Cabe anotar que cuando se ejecuta una operacion, la calculadora automáticamente vacía su lista de operandos anterior y deja únicamente el resultado de la operación solicitada siempre y cuando esta sea valida. Esto permite que la sesion pueda seguir siendo usada y que el resultado de la operacion inmediatamente anterior haga parte de los nuevos operandos.

Tecnologías asociadas:
* Java SE 1.8
* Maven
* JUnit 5.5.2
* Log4j 2.12
* Jersey 2.29 (para mapeo de objetos Java a REST)
* Hibernate 5.4.1
* H2 Database 1.4

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

### Auditoria de Sesiones
---
Se implementó un sistema que registra la auditoria de los diferentes en una base de datos relacional en memoria (H2) y se expuso un API REST que permite consultar para un id de sesion especifico todas las operaciones que hayan sido realizadas dentro de la sesion.

## Pruebas a Servicios Rest
---
Los servicios REST que provee la aplicacion pueden ser probados a traves de un cliente REST, como Postman. Si se cuenta con esta aplicación, puede utilizar el archivo `src/main/resources/CalculadoraRest.postman_collection.json` para importar en postman una colección con los request de prueba para cada una de las cuatro operaciones.

### API de Calculadora
Servicios expuestos para la calculadora:

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
- Si el identificacdor de sesion ingresado no existe, la respuesta es de código HTTP 400 (Bad Request).

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
- Si la operacion ingresada no es permitida, la respuesta es de código HTTP 400 (Bad Request).
- Si el identificacdor de sesion ingresado no existe, la respuesta es de código HTTP 400 (Bad Request).

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

### API de Auditoria
Servicios expuestos para auditoria de sesiones de calculadora:

#### Metodo consultar

*URL*

<http://servidor:puerto/calculadora/audit/{idSesion}>

*Verbo HTTP*

GET

*Request*

Este request no contiene payload. El id de sesion que se desea consultar va como parte de la URL solicitada:

	http://localhost:8080/calculadora/audit/e0570a43-72e0-4feb-ad2b-bbfbbd043949

*Response*

En la respuesta se encuentran listadas todas las acciones ejecutadas dentro del id de sesion especificado, si se encuentran. Para cada entrada aparecen el identificador de la entrada de auditoría, el id de sesión, la accion ejecutada, el valor de la accion si aplica y la fecha en que la accion fue ejecutada (en formato milisegundos)

	[
    {
        "id": 1,
        "idSesion": "e0570a43-72e0-4feb-ad2b-bbfbbd043949",
        "operacion": "iniciarSesion",
        "valor": "",
        "fechaEntrada": 1573719540501
    },
    {
        "id": 2,
        "idSesion": "e0570a43-72e0-4feb-ad2b-bbfbbd043949",
        "operacion": "adicionar",
        "valor": "4",
        "fechaEntrada": 1573719551959
    },
    {
        "id": 3,
        "idSesion": "e0570a43-72e0-4feb-ad2b-bbfbbd043949",
        "operacion": "multiplicacion",
        "valor": "4.0",
        "fechaEntrada": 1573719559729
    }
	] 