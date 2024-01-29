# README - Laboratorio de Construcción de Servidor HTTP
Jose Alejandro correa Rodriguez



Este repositorio contiene el código fuente de un servidor HTTP de tipo fachada, diseñado para establecer una conexión por socket con un servicio API REST para la consulta de información sobre películas.

## Empezando

### Directorios

En el proyecto, encontrarás dos directorios principales:

1. **tallerCasa:** Contiene material relacionado con el taller .
2. **app:** Contiene material relacionado con trabajo en clase y no es necesario para la ejecución del servidor.


## En Funcionamiento

### ¿Cómo funciona?

1. Dirígete al directorio `tallerCasa`.
2. Ejecuta la clase `HTTPServer`.
3. En tu navegador preferido (por ejemplo, Firefox), visita `localhost:35000`, que es el puerto del socket que se abre.

<<<<<<< HEAD
En la interfaz web, encontrarás un input para realizar  una consulta  con el método GEt, para ejecutar precionar el boton  "Submit".
=======
En la interfaz web, encontrarás un input para realizar la consulta. Escribe el nombre de la película y haz clic en "Submit".
>>>>>>> 898830daacfc4e852a3ca6cf443365f32032bda0

### Lógica del Servidor

El servidor fachada recibe mensajes en formato JSON de un servidor que se conecta al API de OMBapi. Realiza la consulta al API solo si la búsqueda no está en el caché, que es resistente a la concurrencia.

La respuesta del API en formato JSON se procesa utilizando la librería JSON incluida en Java (presente en el archivo `pom.xml`). Se realiza el mapeo del JSON para construir un listado organizado que se muestra de forma amigable en la interfaz HTML.

## Herramienta usadas

### Maven
### git
### netbeans
