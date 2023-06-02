INSERT INTO regiones (id, nombre) VALUES (1,'Sudamerica');
INSERT INTO regiones (id, nombre) VALUES (2,'Europa');
INSERT INTO regiones (id, nombre) VALUES (3,'Africa');




INSERT INTO clientes(id, region_id, nombre, apellido, email, create_at) VALUES(1,1, 'Camilo', 'Guzman', 'profesor@gmail.com', '2018-01-01');   
INSERT INTO clientes(id, region_id, nombre, apellido, email, create_at) VALUES(2,2, 'Ronald', 'Sanchez', 'ronaldss2000hotmail.com', '2022-02-22');  
INSERT INTO clientes(id, region_id, nombre, apellido, email, create_at) VALUES(3,3, 'Stiven', 'Garcia', 'manchazgarcia@gmail.com', '2023-05-16');     

INSERT INTO productos (nombre, precio, create_at) VALUES('Apple ipad shuffle', 1500000, NOW());    
INSERT INTO productos (nombre, precio, create_at) VALUES('TV samsung', 1250000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('LG refrigerador', 2550000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Nintendo Switch', 1600000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Xbox series x', 2550000, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Play station 5', 3250000, NOW());



INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Factura 1', 'promocion 1', 1, NOW());                              
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1); 
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Factura 2', 'promocion 1', 2, NOW());   
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 2, 3); 
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Factura 3', 'promocion 1', 3, NOW());   
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 3, 2); 


