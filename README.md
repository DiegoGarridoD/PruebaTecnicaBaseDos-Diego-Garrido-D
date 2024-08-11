# Prueba Técnica Base Dos: Diego Garrido

El problema a trabajar es el control de stock de discos en una tienda de música. Se incluye un diagrama de clases que presenta las clases involucradas y sus funcionalidades.

## Requerimientos

- Java 22
- npm 9.6.4
- node 20.16.0
- Entorno de desarrollo (opcional): IntelliJ IDEA para el backend y Visual Studio Code para el frontend.
- MySQL 8.0 (opcionalmente, se puede utilizar MySQL Workbench para la gestión de la base de datos)

## Configuración de Base de Datos
Este proyecto utiliza MySQL como base de datos. El archivo application.properties se encuentra en la carpeta resources del backend y debe configurarse con las credenciales de la base de datos correspondientes. Las tablas de la base de datos se generan automáticamente al ejecutar por primera vez la aplicación.  Además, se incluye un archivo SQL en la carpeta `/Base de datos` disponible en el repositorio, que contiene una exportación de la base de datos utilizada en este proyecto, facilitando así el proceso de poblar la base de datos.

## Construcción del Backend

1. Clona el repositorio en tu máquina local.
2. Abre el proyecto en IntelliJ IDEA.
3. Asegúrate de tener configurado un SDK de Java 22.
4. Ejecuta el proyecto presionando el ícono de "Play" en IntelliJ IDEA o utilizando el comando `mvn spring-boot:run` en la terminal.

## Ejecución y Pruebas del Backend

- Las pruebas unitarias se pueden ejecutar directamente en IntelliJ IDEA. Puedes correr todas las pruebas utilizando la opción de "Run All Tests" o ejecutarlas individualmente desde cada clase de prueba.
- El repositorio incluye un archivo `postman.json` que puedes importar en Postman para realizar pruebas de API sobre las distintas clases.

## Construcción del Frontend

1. Navega a la carpeta del frontend en la terminal.
2. Ejecuta `npm install` para instalar todas las dependencias.

## Ejecución del Frontend

- Abre la terminal en la carpeta del frontend y ejecuta el comando `npm start`.
- La aplicación se abrirá automáticamente en el navegador en la dirección `http://localhost:3000`.

## Funciones
Se disponen funciones de utilidad para la gestion de discos de una tienda, entre ellas destacan funciones CRUD por cada clase, junto con el filtrado de discos disponibles por genero.



