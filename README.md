
# Foro Hub

Foro Hub es una aplicación web desarrollada en Java y Spring Boot diseñada para la creación y gestión de foros de discusión. Este proyecto está configurado para utilizar Java (versión 17 en adelante) y Maven, y está empaquetado en formato JAR.

## Configuración del Proyecto

Este proyecto ha sido configurado utilizando Spring Initializr con las siguientes opciones:

- **Java**: Versión 17 en adelante
- **Maven**: Versión 4
- **Spring Boot**: Utilizado como framework principal
- **Formato del Proyecto**: JAR

## Dependencias

Las siguientes dependencias han sido agregadas al proyecto a través de Spring Initializr:

- **Lombok**: Para minimizar el código repetitivo
- **Spring Web**: Para el desarrollo de aplicaciones web y RESTful
- **Spring Boot DevTools**: Para facilitar el desarrollo con herramientas de recarga en caliente
- **Spring Data JPA**: Para el acceso a datos usando JPA con Hibernate
- **Flyway Migration**: Para la gestión de migraciones de base de datos
- **MySQL Driver**: Para la conexión a bases de datos MySQL
- **Validation**: Para la validación de datos
- **Spring Security**: Para la seguridad de la aplicación

## Diagrama de Base de Datos

La base de datos utilizada en Foro Hub está diseñada para almacenar la información necesaria para la creación y gestión de tópicos. A continuación, se presenta la estructura de la tabla principal:

```markdown
|-------------------------------------|
|           Topicos Tabla             |
|-------------------------------------|
| id             | (1)                |
| titulo         | HOLA MUNDO         |
| mensaje        | TEXTO              |
| fecha creación | DIA/MES/AÑO        |
| status         | ACTIVO             |
| autor          | NOMBRE             |
| curso          | VARCHAR            |
|-------------------------------------|
```
## Endpoints CRUD

El proyecto Foro Hub implementa las siguientes operaciones CRUD para gestionar los tópicos:

- **Registrar/Crear Tópico (POST)**: `/topics`
- **Mostrar Todos los Tópicos (GET)**: `/topics`
- **Mostrar Tópico por ID (GET)**: `/topics/{id}`
- **Actualizar Tópico (PUT)**: `/topics/{id}`
- **Eliminar Tópico (DELETE)**: `/topics/{id}`

# Funcionalidades del Proyecto

## 1. Crear un Tópico (POST)
Para crear un nuevo tópico, se debe enviar una solicitud POST con los siguientes datos:

```json
{
   "titulo": "Introducción a Java",
   "mensaje": "Este es un tópico sobre Java.",
   "autor": "Juan Pérez",
   "curso": "Desarrollo Backend"
}
```
Endpoint: POST`http://localhost:8080/topicos`

http POST /api/topicos

## 2. Obtener Todos los Tópicos (GET)
Para obtener la lista completa de tópicos:

Endpoint: GET`http://localhost:8080/topicos`

http GET /api/topicos


## 3. Obtener un Tópico por ID (GET)
   Para obtener los detalles de un tópico específico por su id:

Endpoint: GET`http://localhost:8080/topicos/{id}`

http GET /api/topicos/{id}


## 4. Actualizar un Tópico (PUT)
   Para actualizar un tópico existente:

Endpoint: PUT`http://localhost:8080/topicos/{id}`

http PUT /api/topicos/{id}


 ### Ejemplo de solicitud:

```json
{
"titulo": "Actualización sobre Java",
"mensaje": "Este es el mensaje actualizado sobre Java.",
"autor": "Juan Pérez",
"curso": "Desarrollo Backend"
}
```
## 5. Eliminar un Tópico (DELETE)
   Para eliminar un tópico por su id:

Endpoint: DELETE`http://localhost:8080/topicos/{id}`

http DELETE /api/topicos/{id}


## Seguridad
La aplicación implementa seguridad utilizando Spring Security y JWT para la autenticación de usuarios.

1. Autenticación con `JWT`
   Para generar un token `JWT`, el usuario debe enviar las credenciales mediante una solicitud `POST`:

Endpoint: POST`http://localhost:8080/login`

http
POST /api/auth/login
Ejemplo de solicitud:

```json
{
"username": "usuario",
"password": "contraseña"
}
```
Si las credenciales son correctas, el servidor devolverá un JWT que se debe incluir en el encabezado Authorization de las solicitudes subsecuentes:

```http
Authorization: Bearer <jwt_token>
```

## Instalación y Ejecución

Para instalar y ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio en tu máquina local:
    ```bash
    git clone https://github.com/tu-usuario/foro-hub.git
    ```

2. Navega al directorio del proyecto:
    ```bash
    cd foro-hub
    ```

3. Compila y ejecuta la aplicación utilizando Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Configuración de la Base de Datos

Asegúrate de tener una base de datos MySQL configurada y actualiza las propiedades de conexión en `application.properties` o `application.yml`:

```yaml
spring.datasource.url=jdbc:mysql://localhost:3306/nombre_base_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.secret=${JWT_SECRET:123456}
````