# market-virtual

1. Login
En este caso, hemos aplicado Spring Security, que es un marco (framework) de seguridad proporcionado por Spring Framework. Spring Security es ampliamente utilizado en aplicaciones Java para manejar aspectos de autenticación y autorización.

Características principales que hemos implementado:
Configuración personalizada de seguridad:

Utilizamos una clase SecurityConfig con el método filterChain para definir cómo se gestionan las solicitudes HTTP. Aquí, permitimos el acceso público a los endpoints de login y registro (/api/usuarios/login y /api/usuarios/registrar) con permitAll(), mientras que cualquier otra solicitud requiere autenticación con .authenticated().
Codificación de contraseñas:

Implementamos la codificación de contraseñas usando BCryptPasswordEncoder, que es un mecanismo robusto y recomendado por Spring Security para almacenar contraseñas de forma segura.
Gestión de autenticación:

Validamos las credenciales de usuario (email y contraseña) contra los datos almacenados en la base de datos.
Para esto, usamos una implementación de servicio (UsuarioServiceImpl) que compara contraseñas codificadas usando BCryptPasswordEncoder.matches().
Integración con la base de datos:

Aprovechamos la anotación @Enumerated para mapear roles (CLIENTE, ADMINISTRADOR) y almacenar la fecha de creación del usuario (creado_en) como un LocalDateTime.
¿Por qué usamos Spring Security?
Spring Security nos proporciona una solución estándar y probada para manejar la seguridad en aplicaciones web o APIs REST. Algunas ventajas son:

Flexibilidad: Puedes personalizar fácilmente la configuración para adaptarla a tus necesidades.
Protección contra ataques comunes: Incluye protección integrada contra ataques como CSRF, XSS, y más.
Codificación de contraseñas: Proporciona herramientas como BCryptPasswordEncoder para el manejo seguro de contraseñas.
Ecosistema Spring: Se integra perfectamente con otros módulos de Spring, como Spring Boot, Data JPA, etc.

2.Utilizar JWT (JSON Web Token) para autenticación

En lugar de depender de sesiones o autenticación básica, usar JWT es una práctica recomendada para APIs REST. Con esto:

Los usuarios reciben un token al iniciar sesión.
El token se incluye en las solicitudes posteriores (en el encabezado Authorization).
Spring Security valida el token para garantizar el acceso seguro.


