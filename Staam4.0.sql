DROP database Staam3_5;
create DATABASE Staam3_5;
use Staam3_5;

create table Usuarios (
ID_Usuarios int not null primary key auto_increment,
Username varchar(45) not null UNIQUE,
Email varchar(45) not NULL UNIQUE,
password_hash VARCHAR(255) NOT NULL,
Balance decimal(8,2) DEFAULT 0.00,
FechaCreacion date,
Ubicacion varchar(45) null,
Telefono varchar(20)
);

create table SolicitudAmistad(
ID_From int not null,
ID_To int not null,
FechaSoli datetime not null,
Foreign key (ID_From) references Usuarios(ID_Usuarios) on delete cascade,
Foreign key (ID_To) references Usuarios(ID_Usuarios) on delete cascade);

create table Amigos(
ID_Cuenta int not null,
ID_Amigo int not null,
Fecha_Acep datetime not null,
Foreign key (ID_Cuenta) references Usuarios(ID_Usuarios) on delete cascade,
Foreign key (ID_Amigo) references Usuarios(ID_Usuarios) on delete cascade);

create table Publisher (
ID_Publisher int not null primary key auto_increment,
Nombre varchar(45) not null UNIQUE,                                        
Email varchar(35) not null unique,
password_hash VARCHAR(255) NOT NULL,
FechaCreacion date NULL,
Ubicacion varchar(45) not null,
Descripcion varchar(50) not null,
Imagen LONGBLOB NOT NULL
);

create table Juegos (
ID_Juegos int not null primary key auto_increment,
Nombre varchar(45) not null,
Precio decimal(8,2) not null,
Descripcion varchar(45) not null,
FLanzamiento date null,
ID_Publisher int not null,
pesoJuego int not null,
requirimientoRam int not null,
requirimientoProcesador varchar(25) not null,
requirimientoGrafica varchar(25) not null,
fotoPortada LONGBLOB NOT NULL,
foreign key (ID_Publisher) references Publisher(ID_Publisher) ON DELETE CASCADE
);

CREATE table Transacciones (
ID_Transaccion int not null primary key auto_increment,
ID_Usuario int not null,
FechaTrans date not null,
Subtotal decimal(8,2) not null,
ID_Juego int not null,
foreign key (ID_Usuario) references Usuarios(ID_Usuarios) on delete cascade
);

create table Carrito(
ID_Carrito int not null primary key auto_increment,
Fecha_Carrito date not null,
ID_Juego int not null,
ID_Usuario int not null,
foreign key (ID_Usuario) references Usuarios(ID_Usuarios) on delete cascade,
Foreign key (ID_Juego) references Juegos(ID_Juegos) on delete cascade);

create table SesionJuego(
ID_Sesion int not null primary key auto_increment,
InicioSesion datetime DEFAULT CURRENT_TIMESTAMP,
DuracionSesion int default 0, -- En MInutos
ID_User int not null,
ID_Juego int not null,
Foreign key (ID_Juego) references Juegos(ID_Juegos) on delete cascade,
Foreign key (ID_User) references Usuarios(ID_Usuarios) on delete cascade);

SELECT * FROM SesionJuego;
DELETE FROM SesionJuego WHERE DuracionSesion = 0 AND ID_User = 1;


create table juegosBiblioteca(
ID_Biblio int not null primary key auto_increment,
FechaCompra datetime not null,
FechaDescarga datetime not null,
ID_Juego int not null,
ID_Usuario int not null,
instalado boolean default false not null,
Foreign key (ID_Usuario) references Usuarios(ID) on delete cascade,
Foreign key (ID_Juego) references Juegos(ID_Juegos) on delete cascade);



create table Updates(
ID_Update int not null primary key auto_increment,
FechaUp date not null,
VersionJuego varchar(10) not null,
PesoUp float(5,2) not null,
ID_JuegoBiblio int not null,
ID_Juego int not null,
Foreign key (ID_JuegoBiblio) references BibliotecaJuegos(ID_Biblio) on delete cascade,
Foreign key (ID_Juego) references Juegos(ID_Juegos) on delete cascade);

create table Noticias(
ID_Noticia int not null primary key auto_increment,
Descripcion varchar(100) not null,
ID_Juegos int not null,
ID_Usuario int not null,
Foreign key (ID_Juegos) references Juegos(ID_Juegos) on delete cascade,
Foreign key (ID_Usuario) references Usuarios(ID_Usuarios) on delete cascade);

create table ResJuegos(
ID_Res int not null primary key auto_increment,
Cali BOOLEAN NOT NULL, -- True:Bueno  , False:Malo
Comentario varchar(80) not null,
ID_Usuario int not null,
ID_Juego int NOT NULL,
Foreign key (ID_Usuario) references Usuarios(ID_Usuarios) on delete cascade,
FOREIGN KEY (ID_Juego) REFERENCES Juegos(ID_Juegos) ON DELETE CASCADE);



--Trigger para que agrege la fecha actual cuando cree un usuario
DELIMITER //
CREATE TRIGGER setFechaCreacionUsuario
BEFORE INSERT ON Usuarios
FOR EACH ROW
BEGIN
    IF NEW.FechaCreacion IS NULL THEN
        SET NEW.FechaCreacion = CURDATE();
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER setFechaCreacionPublisher
BEFORE INSERT ON Publisher 
FOR EACH ROW
BEGIN
    IF NEW.FechaCreacion IS NULL THEN
        SET NEW.FechaCreacion = CURDATE();
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER setFechaJuego
BEFORE INSERT ON Juegos 
FOR EACH ROW
BEGIN
    IF NEW.FLanzamiento IS NULL THEN
        SET NEW.FLanzamiento = CURDATE();
    END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER setFechaTransaccion
BEFORE INSERT ON Transacciones 
FOR EACH ROW
BEGIN
    IF NEW.FechaTrans IS NULL THEN
        SET NEW.FechaTrans = CURDATE();
    END IF;
END;
//
DELIMITER ;

-- Procedimiento para agregar juegos a la biblioteca del comprador una vez terminada la compra 
DELIMITER //
CREATE PROCEDURE AgregarJuego(IN p_ID_Usuarios INT, IN p_ID_Juegos INT)
BEGIN
-- Declaramos una variable para ver si el usuario cuenta ya con el juego 
DECLARE Propiedad INT DEFAULT 0;
-- Se realiza un conteo con la variable nueva en Biblioteca para ver si el Juego ya le pertenece al usuario 
    SELECT COUNT(*) INTO Propiedad FROM BibliotecaJuegos WHERE ID_Usuarios = p_ID_Usuarios AND ID_Juegos = p_ID_Juegos;
-- Si el Usuario no tiene el juego se le añade a su biblioteca 
    IF Propiedad = 0 THEN
        INSERT INTO BibliotecaJuegos (ID_Usuarios, ID_Juegos, FechaCompra)
        VALUES (p_ID_Usuarios, p_ID_Juegos, NOW());
        SELECT 'Juego agregado a la biblioteca con éxito.' AS Mensaje;
    ELSE
        SELECT 'El juego ya existe en la biblioteca de este cliente.' AS Mensaje;
    END IF;
END
//
DELIMITER ; 

-- Procedimiento para Verificar si 2 usuarios tienen una amistad o solicitudes pendientes entre sí 
DELIMITER //
CREATE PROCEDURE VerificarEstadoAmistad (IN p_ID_Usuario1 INT, IN p_ID_Usuario2 INT)
BEGIN
-- Declaramos variables de amistad y solicitudes entre usuarios
    DECLARE Amistad INT DEFAULT 0;
    DECLARE Solicitud1_2 INT DEFAULT 0; 
    DECLARE Solicitud2_1 INT DEFAULT 0; 
-- Declaramos variables para ver si existían los usuarios
    DECLARE U1existe INT DEFAULT 0;
    DECLARE U2existe INT DEFAULT 0;
-- Se validan que ambos usuarios no sean el mismo
    IF p_ID_Usuario1 = p_ID_Usuario2 THEN
        SELECT 'Error: Los IDs de usuario no pueden ser el mismo.' AS EstadoAmistad; 
        -- Aquí queriamos colocar un comando para salir del procedimiento una vez dada la condición
    END IF;
-- Verificamos que ambos usuarios existan, contando que sus ID´s existan en la tabla Usuarios
    SELECT COUNT(*) INTO U1existe FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario1;
    SELECT COUNT(*) INTO U2existe FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario2;
    IF U1existe = 0 THEN 
		SELECT 'Error: El Usuario1 no existe.' AS EstadoAmistad;
    ELSEIF U2existe = 0 THEN 
		SELECT 'Error: El Usuario2 no existe.' AS EstadoAmistad;
    ELSE
    -- Se verifica los estados de amistad de ambos comparando si ya estan anexados como amigos en la cuenta del otro
        SELECT COUNT(*) INTO Amistad FROM Amigos WHERE (ID_Cuenta = p_ID_Usuario1 AND ID_Amigo = p_ID_Usuario2)
			OR (ID_Cuenta = p_ID_Usuario2 AND ID_Amigo = p_ID_Usuario1);
        IF Amistad > 0 THEN
            SELECT 'Son amigos.' AS EstadoAmistad;
        ELSE
        -- Se contabiliza y verifica si hay solicitudes de amistad pendientes entre ambos usuarios
            SELECT COUNT(*) INTO Solicitud1_2 FROM SolicitudAmistad WHERE ID_From = p_ID_Usuario1 AND ID_To = p_ID_Usuario2;
            SELECT COUNT(*) INTO Solicitud2_1 FROM SolicitudAmistad WHERE ID_From = p_ID_Usuario2 AND ID_To = p_ID_Usuario1;
		-- Si hay solicitud entre ambos o de uno a otro salta una notificación al respecto
            IF Solicitud1_2 > 0 AND Solicitud2_1 > 0 THEN
                SELECT 'Solicitud cruzada pendiente entre ambos.' AS EstadoAmistad;
            ELSEIF Solicitud1_2 > 0 THEN
                SELECT CONCAT('Solicitud pendiente de ', (SELECT Username FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario1), ' a ', (SELECT Username FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario2), '.') AS EstadoAmistad;
            ELSEIF Solicitud2_1 > 0 THEN
                SELECT CONCAT('Solicitud pendiente de ', (SELECT Username FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario2), ' a ', (SELECT Username FROM Usuarios WHERE ID_Usuarios = p_ID_Usuario1), '.') AS EstadoAmistad;
            ELSE
			-- En caso de no haber solicitudes entre ninguno de los 2:
                SELECT 'Sin relación de amistad ni solicitudes pendientes.' AS EstadoAmistad;
                -- Mismo caso con la línea 200
            END IF;
        END IF;
    END IF;
END //
DELIMITER ;

INSERT INTO ResJuegos(Cali , Comentario , ID_Usuario ,ID_Juego) VALUES(FALSE , "GG" , 4 , 6);

DELIMITER //

CREATE FUNCTION obtenerPorcentajeAprovacion(idGame int)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE porcentajeGustado DECIMAL(10,2);
    DECLARE numeroGustado INT;
    DECLARE totalResenas INT;
	SELECT Count(*) INTO numeroGustado FROM ResJuegos WHERE ID_Juego = idGame AND Cali = 1;
    SELECT Count(*) INTO totalResenas FROM ResJuegos WHERE ID_Juego = idGame;
    IF totalResenas = 0 THEN
		return -1;
    END IF;
    SET porcentajeGustado = numeroGustado / totalResenas;
    RETURN porcentajeGustado;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION obtenerTiempoJugado(idGame int , idUser int)
RETURNS int
DETERMINISTIC
BEGIN
    DECLARE totalMinutos int;
	SELECT sum(DuracionSesion) INTO totalMinutos FROM SesionJuego WHERE ID_Juego = idGame AND ID_User = idUser;
    IF totalMinutos IS NULL THEN
		return 0;
    END IF;
    RETURN totalMinutos;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION copiasVendidas(idGame int)
RETURNS int
DETERMINISTIC
BEGIN
    DECLARE totalVendidas int;
	SELECT count(*) INTO totalVendidas FROM juegosBiblioteca WHERE ID_Juego = idGame;
    RETURN totalVendidas;
END //

DELIMITER ;

DELIMITER //

CREATE FUNCTION jugadoresUltimos7DIas(idGame int)
RETURNS int
DETERMINISTIC
BEGIN
    DECLARE jugadores int;
	SELECT count(*) INTO jugadores from SesionJuego WHERE InicioSesion BETWEEN NOW() - INTERVAL 7 DAY AND NOW() AND ID_Juego =idGame GROUP BY ID_User LIMIT 1;	
    RETURN jugadores;
END //

DELIMITER ;


SELECT count(*) from SesionJuego WHERE InicioSesion BETWEEN NOW() - INTERVAL 7 DAY AND NOW() AND ID_Juego =6 GROUP BY ID_User LIMIT 1;	

