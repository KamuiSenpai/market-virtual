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

-- Nueva Tabla de Categorías
CREATE TABLE categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
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

INSERT INTO categorias (nombre, descripcion, creado_en) VALUES
('Envases', 'Productos relacionados con envases para alimentos o bebidas', CURRENT_TIMESTAMP),
('Limpieza', 'Productos para la limpieza y desinfección', CURRENT_TIMESTAMP),
('Abarrotes', 'Alimentos básicos y productos de despensa', CURRENT_TIMESTAMP),
('Oficina', 'Suministros y artículos para oficina', CURRENT_TIMESTAMP),
('Bebidas', 'Productos líquidos para consumo', CURRENT_TIMESTAMP);


INSERT INTO productos (nombre, descripcion, precio, categoria_id, unidad_medida_id, activo, imagen_url, creado_en) VALUES
('Vaso de papel 12oz', 'Vaso de papel ecológico para bebidas calientes', 0.50, 1, 1, TRUE, 'https://example.com/imagenes/vaso_papel.jpg', CURRENT_TIMESTAMP),
('Desinfectante multisuperficie', 'Desinfectante líquido para múltiples superficies', 5.99, 2, 2, TRUE, 'https://example.com/imagenes/desinfectante.jpg', CURRENT_TIMESTAMP),
('Arroz blanco 1kg', 'Arroz blanco de grano largo', 3.20, 3, 3, TRUE, 'https://example.com/imagenes/arroz_blanco.jpg', CURRENT_TIMESTAMP),
('Cuaderno universitario', 'Cuaderno universitario de 100 hojas', 2.50, 4, 4, TRUE, 'https://example.com/imagenes/cuaderno.jpg', CURRENT_TIMESTAMP),
('Agua mineral 500ml', 'Botella de agua mineral sin gas', 1.00, 5, 1, TRUE, 'https://example.com/imagenes/agua_mineral.jpg', CURRENT_TIMESTAMP);


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


SELECT * from usuarios