DataFlowCorp: Automatización de Pruebas de API 

Este proyecto contiene una suite de pruebas de automatización para la API de usuarios de reqres.in. El objetivo es validar la funcionalidad de los principales endpoints CRUD (Create, Read, Update, Delete) utilizando un enfoque de pruebas de integración.

Tecnologías Utilizadas
Java 11+: Lenguaje de programación.

Maven: Gestor de dependencias y herramienta de construcción.

JUnit 5: Framework para la escritura y ejecución de pruebas unitarias.

REST Assured: Biblioteca para simplificar las pruebas de APIs REST.

Hamcrest: Conjunto de "matchers" para validaciones de aserción más legibles.

Configuración del Proyecto
Para ejecutar las pruebas, clona el repositorio y asegúrate de tener Java y Maven instalados en tu sistema.

Clonar el repositorio:

Bash

git clone https://github.com/hectorhiguera44/DataFlowCorp.git
cd DataFlowCorp
Instalar las dependencias de Maven:
Maven se encargará de descargar todas las dependencias listadas en el archivo pom.xml.

Bash

mvn clean install
Ejecutar las pruebas:
Puedes ejecutar todas las pruebas desde la terminal:

Bash

mvn test
O, si usas un IDE como IntelliJ IDEA, puedes ejecutar la suite completa de pruebas desde la clase UserApiTests.java.

Pruebas Implementadas
La suite de pruebas UserApiTests.java incluye los siguientes casos de prueba:

Prueba	Tipo de Solicitud	Descripción
testGetUserById()	GET	Valida que se puede obtener un usuario existente por su ID.
testCreateUser()	POST	Verifica la creación exitosa de un usuario con datos válidos.
testUpdateUser()	PUT	Comprueba la actualización de los datos de un usuario existente.
testDeleteUser()	DELETE	Asegura que un usuario puede ser eliminado correctamente.
testGetNonExistentUser()	GET	Prueba el comportamiento de la API al solicitar un usuario que no existe.
testCreateUser_NoBody()	POST	Valida que la API maneja correctamente las solicitudes POST con un cuerpo de datos vacío.


 Hallazgos y Comportamiento de la API
Comportamiento de la API de prueba: Es importante notar que la API reqres.in es una API de simulación. 
Por diseño, algunas de sus respuestas, como el 401 Unauthorized con el mensaje "Missing API key", 
son un comportamiento simulado para probar el manejo de errores en las aplicaciones cliente. 
Las pruebas de este proyecto están diseñadas para interactuar con este comportamiento y validar las respuestas esperadas.

Validaciones: Se ha observado que la API proporciona validaciones básicas (por ejemplo, para solicitudes sin cuerpo), 
lo que demuestra una robustez aceptable para una API de prueba.
