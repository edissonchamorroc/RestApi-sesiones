# RestApi Sesiones

Actualmente muchas aplicaciones, como los portales transaccionales, presentan vulnerabilidad de multiple sesiones generadas a través de diferentes
dispositivos como laptos, celular, etc. Es por ello que se propone como solución la construcción de una API la cual se encargue de manejar las 
sesiones.

El programa expone servicios que permiten la creación, validación y elimianción de una sesión a través de protocolo HTTP.

#### Métodos soportados:

* POST: Crear una nueva sesión
![image](https://user-images.githubusercontent.com/71468355/224377304-54eace52-1914-4973-8a3a-15d1bd4967bd.png)

* POST: Validar una sesión
![image](https://user-images.githubusercontent.com/71468355/224377380-807df2ff-5763-49d1-a15c-e8d748a6fd6b.png)

* DELETE: eliminar una sesión
![image](https://user-images.githubusercontent.com/71468355/224377442-c37d28a7-9299-49ba-a4e1-88aac24a7fb8.png)

## Empezemos :clipboard:

Estas instrucciones le ayudarán a copiar el proyecto y correrlo de manera local para propisitos de desarrollo.

## Prerequisitos

Para construir y correr la aplicación, necesitas previamente tener instalado:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Postgre](https://www.solvetic.com/tutoriales/article/7676-como-instalar-postgresql-en-windows-10/)
- [Intellij IDE](https://support.academicsoftware.eu/hc/es/articles/360006978997-C%C3%B3mo-instalar-IntelliJ-IDEA-Community-Edition) o [eclipse IDE](https://tutobasico.com/instalar-eclipse-windows/)


## Installation :wrench:

1. [Clona el repositorio](https://docs.github.com/es/repositories/creating-and-managing-repositories/cloning-a-repository)
2. 

#### Para ejecutar el servidor:
* [Verificar que el puerto 8080 no esté en uso](https://www.ionos.es/digitalguide/servidores/seguridad/puertos-abiertos/#:~:text=Si%20quieres%20comprobar%20los%20puertos,no%20se%20est%C3%A1n%20conectando%20actualmente.)
* Ejecutar `mvn clean spring-boot:run ` desde la terminal (Estar ubicado en la raiz del proyecto)
* El servidor estará disponible en `http://localhost:8080/swagger-ui/index.html`
* Para validar o eliminar sesión se debe inicialmente crear sesión y copiar el token jwt de respuesta
para posteriormente ubicarlo en el cuerpo de la petición validar o eliminar.

## Author :pencil:

* **[Edisson Chamorro](https://github.com/edissonchamorroc)**
