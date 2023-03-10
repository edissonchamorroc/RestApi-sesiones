# RestApi Sesiones

Actualmente muchas aplicaciones, como los portales transaccionales, presentan vulnerabilidad de multiple sesiones generadas a través de diferentes
dispositivos como laptos, celular, etc. Es por ello que se propone como solución la construcción de una API la cual se encargue de manejar las 
sesiones.

En esta solución se propone exponer servicios que permiten la creación, validación y elimianción de una sesión a través de protocolos HTTP.

Este proyecto fue construido con Java 1.8, Spring boot 2.7.9, Spring web, Spring Data, base de datos Postgresql y contiene pruebas unitarias con JUnit 5 y Mockito para el área 
de repositorios, servicios y controlladores.

#### Métodos soportados:

* POST: Crear una nueva sesión
![image](https://user-images.githubusercontent.com/71468355/224379055-d0cb1248-9b59-4521-a71d-76450a6a9b9a.png)


* POST: Validar una sesión
![image](https://user-images.githubusercontent.com/71468355/224379170-543247bc-e0a3-4ee4-b637-a48044e9e3d8.png)


* DELETE: eliminar una sesión
![image](https://user-images.githubusercontent.com/71468355/224379104-90dbc16b-0ec8-47e2-9a4e-ce8738283d77.png)


## Empezemos :clipboard:

Estas instrucciones le ayudarán a copiar el proyecto y correrlo de manera local para proposito de desarrollo.

## Prerequisitos

Para construir y correr la aplicación, necesitas previamente tener instalado:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Postgre](https://www.solvetic.com/tutoriales/article/7676-como-instalar-postgresql-en-windows-10/)
- [Intellij IDE](https://support.academicsoftware.eu/hc/es/articles/360006978997-C%C3%B3mo-instalar-IntelliJ-IDEA-Community-Edition) o [eclipse IDE](https://tutobasico.com/instalar-eclipse-windows/)


## Instalación :wrench:

1. [Clona el repositorio](https://docs.github.com/es/repositories/creating-and-managing-repositories/cloning-a-repository)
2. Compila las dependencias de Maven
3. Compila el proyecto

#### Para ejecutar el servidor:
* [Verificar que el puerto 8080 no esté en uso](https://www.ionos.es/digitalguide/servidores/seguridad/puertos-abiertos/#:~:text=Si%20quieres%20comprobar%20los%20puertos,no%20se%20est%C3%A1n%20conectando%20actualmente.)
* Ejecutar `mvn clean spring-boot:run ` desde la terminal (Estar ubicado en la raiz del proyecto)
* El servidor estará disponible en `http://localhost:8080/swagger-ui/index.html`
* Para validar o eliminar sesión se debe inicialmente crear sesión y copiar el token jwt de respuesta
para posteriormente ubicarlo en el cuerpo de la petición validar o eliminar.

## Author :pencil:

* **[Edisson Chamorro](https://github.com/edissonchamorroc)**
