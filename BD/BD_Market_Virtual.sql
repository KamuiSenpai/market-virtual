CREATE DATABASE market_virtual;
USE market_virtual;


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL, -- Contraseña encriptada (simulada en este caso)
    rol ENUM('CLIENTE', 'ADMINISTRADOR') DEFAULT 'CLIENTE',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE unidades_medida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    categoria ENUM('Envases', 'Limpieza', 'Abarrotes', 'Oficina', 'Bebidas', 'Otros') NOT NULL,
    unidad_medida_id INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    imagen_url VARCHAR(255),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (unidad_medida_id) REFERENCES unidades_medida(id)
);

CREATE TABLE ordenes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado ENUM('PENDIENTE', 'APROBADA', 'DESPACHADA', 'CANCELADA') DEFAULT 'PENDIENTE',
    direccion_entrega VARCHAR(255) NOT NULL,
    observaciones TEXT,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE detalles_orden (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orden_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);





INSERT INTO usuarios (nombre, email, contrasena, rol) VALUES
('Restaurante Carbon y Punto', 'carbonypunto@gmail.com', 'cliente123', 'CLIENTE'),
('Restaurante El Buen Sabor', 'elbuen@gmail.com', 'cliente123', 'CLIENTE'),
('Administrador del Market', 'admin@market.com', 'admin123', 'ADMINISTRADOR');

INSERT INTO unidades_medida (nombre) VALUES
('UNIDAD'),
('CAJA'),
('FARDO'),
('PAQUETE'),
('BOLSA');

INSERT INTO productos (nombre, descripcion, precio, categoria, unidad_medida_id, activo, imagen_url) VALUES
('Envase Rectangular Grande', 'Envase plástico resistente ideal para delivery', 2.50, 'Envases', 1, TRUE, '/uploads/images/envase1.jpg'),
('Detergente Multiusos', 'Detergente para limpieza general', 10.00, 'Limpieza', 2, TRUE, '/uploads/images/detergente.jpg'),
('Caja de Servilletas', 'Servilletas blancas de alta calidad', 15.00, 'Oficina', 2, TRUE, '/uploads/images/servilletas.jpg'),
('Paquete de Arroz', 'Paquete de arroz de 5kg', 18.50, 'Abarrotes', 4, TRUE, '/uploads/images/arroz.jpg'),
('Bolsa de Azúcar', 'Bolsa de azúcar de 3kg', 12.00, 'Abarrotes', 5, TRUE, '/uploads/images/azucar.jpg');

-- Crear una orden de prueba
INSERT INTO ordenes (usuario_id, total, estado, direccion_entrega, observaciones) VALUES
(1, 47.50, 'PENDIENTE', 'Av. Principal 123, Surquillo, Lima', 'Entregar en horario de almuerzo');

-- Agregar detalles de la orden
INSERT INTO detalles_orden (orden_id, producto_id, cantidad, subtotal) VALUES
(1, 1, 10, 25.00), -- 10 unidades de Envase Rectangular Grande
(1, 3, 1, 15.00),  -- 1 Caja de Servilletas
(1, 5, 2, 7.50);   -- 2 Bolsas de Azúcar

SELECT 
    u.nombre AS nombre_usuario,
    p.nombre AS nombre_producto,
    p.descripcion,
    p.precio,
    p.categoria,
    um.nombre AS unidad_medida,
    d.cantidad AS cantidad_pedida,
    o.creado_en AS fecha_orden
FROM 
    ordenes o
JOIN 
    usuarios u ON o.usuario_id = u.id
JOIN 
    detalles_orden d ON o.id = d.orden_id
JOIN 
    productos p ON d.producto_id = p.id
JOIN 
    unidades_medida um ON p.unidad_medida_id = um.id;


