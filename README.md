# TALLER DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET

Servidor web que soporta múlltiples solicitudes seguidas (no concurrentes). El servidor lee los archivos del disco local y retorna todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes.

# GUÍA

## Herramientas

* Java - Ambiente de desarrollo
* Git - Sistema de control de versiones
* Maven - Administrador de dependencias

# Cómo Instalar

Descargue el archivo .zip o lo clone con el comando:

  * git clone https://github.com/rayo100/Taller2-AREP.git

Una vez descargado el repositorio dirijase al directorio raiz del proyecto y ejecute el comando:

  * mvn exec:java

Finalmente, ingrese al navegador de su preferencia y use el link (El cual es el localhost que corre por el puerto 35000):

  * http://localhost:35000

# Para usar los servicios 

Si desea usar los servicios necesita las siguientes rutas

  * http://localhost:35000/apps/index.html

  * http://localhost:35000/apps/styles.css

  * http://localhost:35000/apps/imagen.jpg

  * http://localhost:35000/apps/app.js

Ademas una pagina web donde involucra todos los archivos:

  * http://localhost:35000/apps/page.html

# Documentación

Para visualizar la documentación de javadoc ejecute el comando:

  * mvn javadoc:javadoc

Y entre a la siguien ruta:

  * ...\Taller2-AREP\target\site\apidocs\

# Tests unitatios

Ubíquese en el directorio principal del repositorio y ejecute el comando:

  * mvn test

# Versionamiento

  * 1.0

# Autor

  * Cesar Manuel Vásquez Montaña

# Extensión

  * Extensibilidad: Para cambiar la API de la cual se obtiene la informacion de las peliculas, solo se debe modificar la url donde se optiene la información, lo cual lo puede realizar en la clase HttpConnection.

  * Modular: HttpConnection realiza el enlace entre la API de peliculas y el socket creado por HttpServer. La clase Seeker unicamente guarda la informacion de peliculas que ya se buscarón anteriormente para que no se busque de nuevo. Por último, en HttpServer tenemos el servidor el cual es el metodo principal que inicia la conexión del socket en espera de solicitudes de información de peliculas, esta a su vez crea la tabla respectiva del JSON que contiene la información de la pelicula buscada.

  * Patrones: Hacemos uso del patron de diseño Fachada, puesto que la clase Seeker recibe toda la consulta inicialmente y antes de preguntarle a la API revisa si ya se encuentra información guardada en el buscador.
