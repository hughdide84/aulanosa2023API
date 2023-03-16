# API de Gestión de Centro de Formación Profesional

Este proyecto es una API desarrollada en JAVA para la gestión de un Centro de Formación Profesional. Esta API permite la gestión de prácticas en empresas de los alumnos, la gestión de proyectos de fin de ciclo, la gestión de alumnos externos que realicen prácticas en el centro y la gestión de matrículas y cobro de pagos. Además, se cuenta con una sección para la gestión de usuarios de la aplicación, cursos y estudios.

## Funcionalidades 📋

La aplicación cuenta con las siguientes funcionalidades:

* Gestión de prácticas en empresas: los usuarios pueden gestionar y asignar prácticas en empresas para los alumnos.
* Gestión de proyectos de fin de ciclo: los usuarios pueden asignar proyectos de fin de ciclo a los alumnos y realizar su seguimiento.
* Gestión de alumnos externos: los usuarios pueden gestionar a los alumnos externos que realizan prácticas en el centro.
* Gestión de matrículas y cobros: los usuarios pueden gestionar las matrículas y realizar el cobro de los pagos.
* Gestión de usuarios: los usuarios pueden gestionar los usuarios de la aplicación, asignar permisos y realizar otras acciones.
* Gestión de cursos y estudios: los usuarios pueden gestionar los cursos y estudios que se ofrecen en el centro de formación.

## Construido con 🛠️

Se ha desarrollado la API en Spring Boot utilizando MySQL como sistema de gestión de bases de datos.

El proyecto se ha creado con MAVEN, incluyendo las siguientes dependencias:

* [Spring Boot Data JPA] (spring-boot-starter-data-jpa de org.springframework.boot) - Para la persistencia en base de datos relacional
* [Spring Boot Web] (spring-boot-starter-web de org.springframework.boot) - Para crear la aplicación web usando Spring MVC
* [Spring Boot Test] (spring-boot-starter-test de org.springframework.boot) - Para las clases de pruebas
* [Spring Boot Validation] (spring-boot-starter-validation de org.springframework.boot) - Para validar objetos de dominio
* [MySQL Connector] (mysql-connector-j de com.mysql) - Controlador JDBC oficial para MySQL
* [Lombok] (lombok de org.projectlombok) - Para generación de código automático en clases POJO mediante anotaciones
* [WEBMVC UI] (springdoc-openapi-starter-webmvc-ui de org.springdoc) - Para la integración entre Spring Boot y Swagger
* [ROME] (springfox-swagger-ui de io.springfox) - Para integrar Swagger, que usaremos para documentar los endpoints del API

## Requisitos 🔧

Para poder ejecutar la aplicación, se requiere tener instalado JAVA 17 o superior.

## Instalación ⚙️

* Clona el repositorio: git clone https://github.com/hughdide84/aulanosa2023API
* Abre la solución en IntelliJ IDEA Community.
* Configurar el acceso a MySQL en fichero application.properties.
* Ejecutar la aplicación.

## Wiki 📖

Puedes encontrar mucho más de cómo utilizar este proyecto en nuestra [Wiki](https://github.com/tu/proyecto/wiki)

## Autores ✒️

Proyecto realizado por alumnos del Ciclo Superior de Desarrollo de Aplicaciones Multiplataforma del CPR Aula Nosa

## Licencia 📄

Este proyecto está bajo la Licencia (Tu Licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles
