Scrum APP

Contexto del Proyecto:
Una agencia de desarrollo de software que gestiona múltiples proyectos de desarrollo web y aplicaciones móviles ha experimentado un crecimiento significativo en los últimos meses. Debido a la expansión de su equipo y la creciente cantidad de proyectos y tareas en curso, han identificado la necesidad de implementar un sistema de gestión de tareas que permita:Controlar el avance de las tareas de cada proyecto.Asignar responsabilidades claras entre desarrolladores, diseñadores, y testers.Supervisar el progreso de los proyectos por parte de los gestores y administradores.Asegurar la seguridad y control de accesos, para que cada empleado pueda ver y gestionar solo la información relevante a su trabajo.

Marcos de competencias:

    Gestión de proyecto con metodologías ágiles
    Desarrollar el backend de una aplicación web (niv. 3)
    Administrar bases de datos (niv. 2)
    Desarrollar y Ejecutar pruebas automatizadas (niv. 3)



Descripción del Proyecto:
El proyecto es un sistema de gestión de tareas basado en una API REST, desarrollado con Java 17 y Spring Boot. La aplicación permite la autenticación de usuarios a través de JWT (JSON Web Tokens) y roles con distintos niveles de acceso utilizando Spring Security. Utiliza una base de datos MySQL para almacenar la información y emplea Docker para contenedores, integrando GitHub Actions para el CI/CD.


Objetivos del Proyecto:
1. Reforzar los conceptos de creación de APIs.
2. Aplicar relaciones de BBDD.
3. Desarrollar un login con Spring Security y JWT
4. Tener primeros contactos con GitHub Actions
Requerimientos Funcionales:1. Roles de Usuario:

    Administrador: Puede gestionar usuarios y tareas.
    Gestor: Puede crear, asignar y gestionar tareas, pero no gestionar usuarios.
    Usuario: Solo puede visualizar y actualizar el estado de sus tareas asignadas.

2. Autenticación y Autorización:

    Implementación de autenticación con JWT para permitir un inicio de sesión seguro.
    Autorización basada en roles con Spring Security, que otorga diferentes permisos según el rol asignado al usuario.

3. CRUD para Tareas:

    Crear: Los gestores pueden crear nuevas tareas y asignarlas a un usuario.
    Leer: Los usuarios pueden visualizar sus tareas, mientras que los gestores pueden ver las tareas asignadas a cualquier usuario.
    Actualizar: Los usuarios pueden actualizar el estado de sus tareas.
    Eliminar: Solo los gestores pueden eliminar tareas.

4. Gestión de Usuarios:

    El administrador puede gestionar el alta, baja y actualización de los usuarios del sistema, así como asignar roles.
    Filtro por Usuario: El gestor puede ver la lista de tareas por usuario y el estado de las mismas

5. Relaciones entre Entidades (Base de Datos):

    Usuario: Tabla que contiene información de los usuarios y su relación con las tareas.
    Tarea: Tabla que almacena las tareas asignadas a los usuarios.
    Proyecto: Relación con la entidad Usuario (Un usuario puede pertenecer a varios proyectos, y un proyecto puede tener varios usuarios). Relación con Tarea (Un proyecto puede tener varias tareas).

Diagrama de Entidades (ER):

    Usuario:
    id (PK)
    username
    password
    email
    role (ADMIN, GESTOR, USUARIO)

Proyecto:

    id (PK)
    nombre

Tarea:

    id (PK)
    nombre
    descripcion
    estado (PENDIENTE,  EN_PROGRESO, COMPLETADO)
    usuario_id (FK) (Relación con Usuario)
    proyecto_id (FK) (Relación con Proyecto)

Endpoints de la API:1. Autenticación:

    POST /api/auth/login: Autenticación de usuario (Devuelve JWT)
    POST /api/auth/register: Registro de un nuevo usuario (Solo accesible por ADMIN)

2. Usuarios:

    GET /api/users: Listar todos los usuarios (Solo ADMIN)
    GET /api/users/{id}: Obtener un usuario por ID (ADMIN o usuario mismo)
    POST /api/users: Crear un nuevo usuario (Solo ADMIN)
    PUT /api/users/{id}: Actualizar un usuario (Solo ADMIN)
    DELETE /api/users/{id}: Eliminar un usuario (Solo ADMIN)

3. Proyectos:

    GET /api/projects: Listar todos los proyectos
    POST /api/projects: Crear un nuevo proyecto (Solo GESTOR y ADMIN)
    PUT /api/projects/{id}: Actualizar un proyecto (Solo GESTOR y ADMIN)
    DELETE /api/projects/{id}: Eliminar un proyecto (Solo GESTOR y ADMIN)

4. Tareas:

    GET /api/tasks: Listar todas las tareas (Solo GESTOR y ADMIN)
    GET /api/tasks/{id}: Obtener una tarea específica (Usuarios pueden obtener solo las suyas)
    POST /api/tasks: Crear una nueva tarea (Solo GESTOR)
    PUT /api/tasks/{id}: Actualizar una tarea (Usuarios solo sus propias tareas)
    DELETE /api/tasks/{id}: Eliminar una tarea (Solo GESTOR)




Extras:

    Dockerizar y subir contenedor a Docker Hub
    Automatizar pruebas con Postman
    Subir a Docker Hub con GitHub Actions

 
Requisitos técnicos del Proyecto:
1. Conocimientos de programación en Java.
2. Conocimientos en Programación Orientada a Objetos (POO).
3. Spring boot
4. Spring Security y JWT
5. Testing
6. Docker
7. GitHub Actions
8. No se puede usar Lombok ni la annotation @Autowired


Modalidades de evaluación:
Grupal, se debe realizar el envió a más tardar el 19 de Septiembre. 

Entregables Esperados:

Código fuente del backend desarrollado en Spring Boot.
Colección de Postman con todos los endpoints y pruebas realizadas (automatizado).
Documentación de API.
Contenedor en Docker Hub 

Recursos Disponibles:
1. Acceso a videos y recursos sobre Spring Security.
2. Soporte y orientación proporcionados por el equipo formativo.


Criterios de rendimiento:
Repositorio en GitHub con el README correspondiente
