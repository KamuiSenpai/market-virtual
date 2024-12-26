CREATE DATABASE market_virtual;
USE market_virtual;

-- Tabla de Usuarios
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('CLIENTE', 'ADMINISTRADOR') DEFAULT 'CLIENTE',
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Unidades de Medida
CREATE TABLE unidades_medida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Estados (Primero, porque otras tablas dependen de ella)
CREATE TABLE estados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de Productos
CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    categoria_id INT NOT NULL,
    unidad_medida_id INT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    imagen_url VARCHAR(255),
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id),
    FOREIGN KEY (unidad_medida_id) REFERENCES unidades_medida(id)
);

-- Tabla de Ordenes
CREATE TABLE ordenes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    estado_id INT NOT NULL,
    direccion_entrega VARCHAR(255) NOT NULL,
    observaciones TEXT,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (estado_id) REFERENCES estados(id)
);

-- Tabla de Historial de Estados
CREATE TABLE ordenes_estados_historial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orden_id INT NOT NULL,
    estado_anterior_id INT  NULL,
    estado_actual_id INT NOT NULL,
    cambiado_por VARCHAR(100),
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (estado_anterior_id) REFERENCES estados(id),
    FOREIGN KEY (estado_actual_id) REFERENCES estados(id)
);

-- Tabla de Detalles de Orden
CREATE TABLE detalles_orden (
    id INT AUTO_INCREMENT PRIMARY KEY,
    orden_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (orden_id) REFERENCES ordenes(id),
    FOREIGN KEY (producto_id) REFERENCES productos(id)
);



INSERT INTO categorias (nombre, descripcion, creado_en)
VALUES 
('Envases', 'Categoría de productos relacionados con envases y empaques.', CURRENT_TIMESTAMP),
('Limpieza', 'Categoría de productos relacionados con la limpieza y el mantenimiento.', CURRENT_TIMESTAMP),
('Abarrotes', 'Categoría de alimentos y otros productos de primera necesidad.', CURRENT_TIMESTAMP),
('Oficina', 'Categoría de productos relacionados con el uso en oficinas.', CURRENT_TIMESTAMP),
('Bebidas', 'Categoría de productos líquidos, como agua y jugos.', CURRENT_TIMESTAMP),
('Otros', 'Categoría de productos que no encajan en otras categorías.', CURRENT_TIMESTAMP);

INSERT INTO unidades_medida (nombre, creado_en)
VALUES 
('Unidad', CURRENT_TIMESTAMP),
('Paquete', CURRENT_TIMESTAMP),
('Caja', CURRENT_TIMESTAMP),
('Litro', CURRENT_TIMESTAMP),
('Gramos', CURRENT_TIMESTAMP),
('Kilogramos', CURRENT_TIMESTAMP);

INSERT INTO productos (nombre, descripcion, precio, categoria_id, unidad_medida_id, activo, imagen_url, creado_en)
VALUES 
('Caja de cartón', 'Caja para empaques y envíos.', 12.50, 1, 3, TRUE, 'https://example.com/images/caja_carton.jpg', CURRENT_TIMESTAMP),
('Detergente líquido', 'Detergente para limpieza de ropa.', 25.99, 2, 4, TRUE, 'https://example.com/images/detergente_liquido.jpg', CURRENT_TIMESTAMP),
('Arroz blanco', 'Bolsa de arroz de 5 kg.', 18.00, 3, 6, TRUE, 'https://example.com/images/arroz_blanco.jpg', CURRENT_TIMESTAMP),
('Silla ergonómica', 'Silla de oficina con diseño ergonómico.', 150.00, 4, 1, TRUE, 'https://example.com/images/silla_ergonomica.jpg', CURRENT_TIMESTAMP),
('Agua mineral', 'Botella de agua de 1 litro.', 1.50, 5, 4, TRUE, 'https://example.com/images/agua_mineral.jpg', CURRENT_TIMESTAMP),
('Bolsa reutilizable', 'Bolsa ecológica para compras.', 3.00, 6, 1, TRUE, 'https://example.com/images/bolsa_reutilizable.jpg', CURRENT_TIMESTAMP);

INSERT INTO estados (nombre, descripcion, creado_en)
VALUES
('PENDIENTE', 'Orden recibida, pendiente de aprobación.', CURRENT_TIMESTAMP),
('APROBADA', 'Orden aprobada, en proceso de despacho.', CURRENT_TIMESTAMP),
('DESPACHADA', 'Orden enviada al cliente.', CURRENT_TIMESTAMP),
('CANCELADA', 'Orden cancelada por el cliente o administrador.', CURRENT_TIMESTAMP);

INSERT INTO ordenes (usuario_id, total, estado_id, direccion_entrega, observaciones, creado_en)
VALUES
(1, 120.50, 1, 'Calle Falsa 123, Lima', 'Entrega entre 9:00 y 12:00', CURRENT_TIMESTAMP),
(1, 300.75, 2, 'Av. Real 456, Arequipa', 'Aprobado por el cliente vía web.', CURRENT_TIMESTAMP),
(1, 450.00, 3, 'Jr. Central 789, Trujillo', 'Orden despachada con transporte propio.', CURRENT_TIMESTAMP),
(1, 50.00, 4, 'Calle Solitaria 321, Cusco', 'Cancelado debido a falta de stock.', CURRENT_TIMESTAMP);

INSERT INTO detalles_orden (orden_id, producto_id, cantidad, subtotal)
VALUES
(5, 1, 2, 25.00), -- Orden 5, Producto 1 (Caja de cartón), Cantidad 2, Subtotal 25.00
(5, 2, 1, 25.99), -- Orden 5, Producto 2 (Detergente líquido), Cantidad 1, Subtotal 25.99
(6, 3, 5, 90.00), -- Orden 6, Producto 3 (Arroz blanco), Cantidad 5, Subtotal 90.00
(7, 4, 1, 150.00), -- Orden 7, Producto 4 (Silla ergonómica), Cantidad 1, Subtotal 150.00
(8, 5, 10, 15.00); -- Orden 8, Producto 5 (Agua mineral), Cantidad 10, Subtotal 15.00


INSERT INTO ordenes_estados_historial (orden_id, estado_anterior_id, estado_actual_id, cambiado_por, fecha_cambio)
VALUES
(5, NULL, 1, 'Sistema', CURRENT_TIMESTAMP),       -- Orden 5: PENDIENTE
(6, 1, 2, 'Administrador', CURRENT_TIMESTAMP),    -- Orden 6: De PENDIENTE a APROBADA
(7, 2, 3, 'Administrador', CURRENT_TIMESTAMP),    -- Orden 7: De APROBADA a DESPACHADA
(8, 1, 4, 'Cliente', CURRENT_TIMESTAMP);          -- Orden 8: De PENDIENTE a CANCELADA






SELECT * FROM usuarios WHERE id = 1;
SELECT * FROM productos;
SELECT * FROM ordenes;
SELECT * FROM estados;
SELECT * FROM ordenes_estados_historial;
SELECT * FROM detalles_orden;


