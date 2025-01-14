# 📚 Adopciones de Mascotas API REST

Una API REST creada con **Spring Boot** para gestionar adopciones de mascotas. Proporciona endpoints para manejar información sobre mascotas, adoptantes y procesos de adopción.

---

## 📝 Tabla de Contenidos

1. [Descripción](#-descripción)
2. [Características](#-características)
3. [Requisitos Previos](#-requisitos-previos)
4. [Instalación](#-instalación)
5. [Uso](#-uso)
6. [Endpoints](#-endpoints)
7. [Tecnologías Utilizadas](#-tecnologías-utilizadas)

---

## 📖 Descripción

Esta API permite a refugios de animales gestionar adopciones de mascotas mediante funcionalidades como:
- Registro y consulta de mascotas disponibles.
- Consultar y crear tipos de mascotas.
- Administración de perfiles de adoptantes.
- Crear un proceso de adopcion, en cual se vincula una mascota y un adoptante.

---

## ✨ Características

- CRUD para mascotas (crear, leer, actualizar, eliminar).
- CRUD para adoptantes.
- Registro de adopciones.
- Seguridad básica con autenticación y autorización (Utilizando Spring Security).

---

## ⚙️ Requisitos Previos

1. **Java 17** o superior.
2. **Maven**.
3. Una base de datos H2.

---

1. Clona este repositorio:

   ```bash
   git clone https://github.com/usuario/adopciones-api.git
   cd adopciones-api

2. Configura el archivo application.properties en src/main/resources/ con las credenciales de tu base de datos:
   
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/adopciones
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña

3. Ejecuta la aplicación

---

## 🚀 Uso

La API estará disponible en http://localhost:8080.

Puedes probar los endpoints utilizando herramientas como Postman o cURL.

---

## 📂 Endpoints

### Mascotas

| Método  | Endpoint            | Descripción                     | Ejemplo de Request Body                     |
|---------|---------------------|---------------------------------|--------------------------------------------|
| GET     | `/api/mascotas`     | Listar todas las mascotas       | _No aplica_                                |
| POST    | `/api/mascotas`     | Registrar una nueva mascota     | `{ "name": "Luna", "age": 3, "type_id": { name: "Perro" } }` |
| GET     | `/api/mascotas/{id}`| Obtener detalles de una mascota | _No aplica_                                |
| GET     | `/api/mascotas/disponibles` | Listar las mascotas disponibles | _No aplica_                                |
| PUT     | `/api/mascotas/{id}`| Actualizar información          | `{ "name": "Luna", "age": 4 }`          |
| DELETE  | `/api/mascotas/{id}`| Eliminar una mascota            | _No aplica_                                |

### Adoptantes

| Método  | Endpoint              | Descripción                          | Ejemplo de Request Body                     |
|---------|-----------------------|--------------------------------------|--------------------------------------------|
| GET     | `/api/usuarios`       | Listar todos los adoptantes          | _No aplica_                                |
| POST    | `/api/usuarios`       | Registrar un nuevo adoptante         | `{ "name": "Juan", "phone": "1234567890" }` |
| GET     | `/api/usuarios/{id}`  | Obtener detalles de un adoptante     | _No aplica_                                |
| PUT     | `/api/usuarios/{id}`  | Actualizar información               | `{ "name": "Juan", "phone": "1234567890" }` |
| DELETE  | `/api/usuarios/{id}`  | Eliminar un adoptante                | _No aplica_                                |

### Tipo de Mascota

| Método  | Endpoint               | Descripción                             | Ejemplo de Request Body                    |
|---------|------------------------|-----------------------------------------|--------------------------------------------|
| GET     | `/api/tipomascota`     | Listar todos los tipo de macota         | _No aplica_                                |
| POST    | `/api/tipomascota`     | Registrar un nuevo tipo de mascota      | `{ "name": "Perro" }` |
| GET     | `/api/tipomascota/{id}`| Obtener detalles de un tipo de mascota  | _No aplica_                                |

### Adopciones

| Método  | Endpoint               | Descripción                           | Ejemplo de Request Body                     |
|---------|------------------------|---------------------------------------|--------------------------------------------|
| POST    | `/api/adopciones`      | Registrar una adopción                | `{ "mascotaId": 1, "adoptanteId": 2 }`     |
| GET     | `/api/adopciones`      | Listar todas las adopciones           | _No aplica_                                |
| GET     | `/api/adopciones/{id}` | Obtener detalles de una adopcion      | _No aplica_                                |


---

## 🔒 Seguridad

Esta API incluye mecanismos de seguridad para garantizar el acceso seguro a los endpoints.

Todas las endpoints a excepcion de la GET de Mascotas y la GET de tipo de mascota, estan configuradas con Spring Security.
Para poder acceder a las demas es necesario poner un usuario y contraseña, Estas la encontraras en archivo **AdopcionAplication**.

Hay diferentes usuarios con diferentes roles y cada rol tiene diferentes permisos, toda esta informacion esta en la misma base de datos.

Cada endpoint se puede acceder con diferentes permiso y en una solo se puede acceder con un tipo de Rol, para saber mas o modificar los permisos en los endpoint lo puedes ver en archivo **SecurityConfig**.

---

## 🛠️ Tecnologías Utilizadas

- Spring Boot para la construcción del backend.
- Spring Security para la seguiridad
- JPA/Hibernate para la interacción con la base de datos.
- Maven para el manejo de dependencias.
- H2.
