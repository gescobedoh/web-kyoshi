-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2017 a las 11:34:55
-- Versión del servidor: 10.1.26-MariaDB
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdkyoshi`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `IDDETALLE` int(11) NOT NULL,
  `cantidadProducto` int(11) NOT NULL,
  `idPedido` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`IDDETALLE`, `cantidadProducto`, `idPedido`, `idProducto`) VALUES
(3, 5, 2, 2),
(4, 1, 3, 1),
(5, 3, 4, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallep`
--

CREATE TABLE `detallep` (
  `IDDETALLEP` int(11) NOT NULL,
  `cantIng` float NOT NULL,
  `idIngrediente` int(11) NOT NULL,
  `idProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `detallep`
--

INSERT INTO `detallep` (`IDDETALLEP`, `cantIng`, `idIngrediente`, `idProducto`) VALUES
(1, 2, 1, 1),
(2, 1, 2, 1),
(3, 0.6, 4, 2),
(4, 0.5, 1, 2),
(5, 0.4, 1, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `IDDIRECCION` int(11) NOT NULL,
  `calledirec` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `distritodirec` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `dptodirec` int(11) DEFAULT NULL,
  `nrodirec` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`IDDIRECCION`, `calledirec`, `distritodirec`, `dptodirec`, `nrodirec`, `idUsuario`) VALUES
(1, 'Ada', 'San Isidro', 6, 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingrediente`
--

CREATE TABLE `ingrediente` (
  `IDINGREDIENTE` int(11) NOT NULL,
  `cantidadIngrediente` float NOT NULL,
  `costeIngrediente` float NOT NULL,
  `nombreIngrediente` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `ingrediente`
--

INSERT INTO `ingrediente` (`IDINGREDIENTE`, `cantidadIngrediente`, `costeIngrediente`, `nombreIngrediente`) VALUES
(1, 10, 3.5, 'Arroz'),
(2, 8, 5, 'Palta'),
(3, 20, 12, 'Queso'),
(4, 6, 9, 'Alga'),
(5, 4, 15, 'Camaron'),
(6, 19, 11, 'Pescado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `IDPEDIDO` int(11) NOT NULL,
  `atenderPedido` tinyint(1) NOT NULL DEFAULT '0',
  `fechaPedido` date NOT NULL,
  `preciofPedido` float NOT NULL,
  `promocionPedido` varchar(50) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`IDPEDIDO`, `atenderPedido`, `fechaPedido`, `preciofPedido`, `promocionPedido`, `idUsuario`) VALUES
(2, 0, '2017-11-21', 71, '2x1', 2),
(3, 1, '2017-11-21', 18, '2x1', 2),
(4, 1, '2017-11-22', 51, 'NADA', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `IDPRODUCTO` int(11) NOT NULL,
  `FOTOPRODUCTO` longblob,
  `nombreProducto` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `precioProducto` float DEFAULT NULL,
  `tipoProducto` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IDPRODUCTO`, `FOTOPRODUCTO`, `nombreProducto`, `precioProducto`, `tipoProducto`) VALUES
(1, NULL, 'Acebichado', 12, 'Rolls'),
(2, NULL, 'Spicy', 13, 'Rolls'),
(3, NULL, 'Tiradito', 15, 'Rolls');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `IDTIPO` int(11) NOT NULL,
  `nombreTipo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`IDTIPO`, `nombreTipo`) VALUES
(1, 'Administrador'),
(2, 'Cliente Registrado'),
(3, 'Cliente Invitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `IDUSUARIO` int(11) NOT NULL,
  `apellidoUsuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `correoUsuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `nombreUsuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `passwordUsuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `telefonoUsuario` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `idTipo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`IDUSUARIO`, `apellidoUsuario`, `correoUsuario`, `nombreUsuario`, `passwordUsuario`, `telefonoUsuario`, `idTipo`) VALUES
(1, 'Peralta', 'sp', 'Sebas', 'admin', '9910452', 1),
(2, 'Lamas', 'jl', 'Jose', 'guest', '2131558', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`IDDETALLE`),
  ADD KEY `FK_Detalle_idPedido` (`idPedido`),
  ADD KEY `FK_Detalle_idProducto` (`idProducto`);

--
-- Indices de la tabla `detallep`
--
ALTER TABLE `detallep`
  ADD PRIMARY KEY (`IDDETALLEP`),
  ADD KEY `FK_DetalleP_idIngrediente` (`idIngrediente`),
  ADD KEY `FK_DetalleP_idProducto` (`idProducto`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`IDDIRECCION`),
  ADD KEY `FK_Direccion_idUsuario` (`idUsuario`);

--
-- Indices de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  ADD PRIMARY KEY (`IDINGREDIENTE`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`IDPEDIDO`),
  ADD KEY `FK_pedido_idUsuario` (`idUsuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IDPRODUCTO`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`IDTIPO`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`IDUSUARIO`),
  ADD KEY `FK_usuario_idTipo` (`idTipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `IDDETALLE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `detallep`
--
ALTER TABLE `detallep`
  MODIFY `IDDETALLEP` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `IDDIRECCION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `ingrediente`
--
ALTER TABLE `ingrediente`
  MODIFY `IDINGREDIENTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `IDPEDIDO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IDPRODUCTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `tipo`
--
ALTER TABLE `tipo`
  MODIFY `IDTIPO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `IDUSUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `FK_Detalle_idPedido` FOREIGN KEY (`idPedido`) REFERENCES `pedido` (`IDPEDIDO`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_Detalle_idProducto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`IDPRODUCTO`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detallep`
--
ALTER TABLE `detallep`
  ADD CONSTRAINT `FK_DetalleP_idIngrediente` FOREIGN KEY (`idIngrediente`) REFERENCES `ingrediente` (`IDINGREDIENTE`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_DetalleP_idProducto` FOREIGN KEY (`idProducto`) REFERENCES `producto` (`IDPRODUCTO`) ON DELETE CASCADE;

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `FK_Direccion_idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`IDUSUARIO`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_pedido_idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`IDUSUARIO`) ON DELETE CASCADE;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `FK_usuario_idTipo` FOREIGN KEY (`idTipo`) REFERENCES `tipo` (`IDTIPO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
