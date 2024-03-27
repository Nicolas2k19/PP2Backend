# VDG Backend

Este proyecto contiene el backend para la aplicación VDG. Proporciona instrucciones sobre cómo configurar y ejecutar el repositorio.

## Instrucciones para correr el repo

1. **Crear una base de datos (MySQL):** Crea una base de datos llamada `vdg` en tu servidor MySQL.

2. **Ejecutar los scripts SQL:** Ejecuta los scripts SQL que contienen los inserts iniciales en tu base de datos `vdg` (archivo `ScriptMysql/vdg.sql`).

3. **Limpiar el proyecto:** En Eclipse, ve a `Project > Clean` para limpiar el proyecto.

4. **Compilar y correr:** Compila el proyecto y ejecútalo.

## Configuración de la base de datos y el puerto

Los parámetros de la base de datos y el puerto se configuran en el archivo `application.properties`, el cual está incluido en el archivo `.gitignore` para evitar conflictos.

La aplicación se ejecuta en el puerto 9090 por defecto.

## Documentación de la API

La documentación de la API se genera automáticamente con Swagger y está disponible en:

[http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

Puedes utilizar esta página para explorar y probar las API proporcionadas por el backend.

## Pruebas con Postman

Se incluye un archivo llamado `Vdg.postman_collection.json`, el cual contiene pruebas de las APIs que puedes importar en Postman.

