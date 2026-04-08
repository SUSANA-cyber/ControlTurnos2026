-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 08-04-2026 a las 03:44:33
-- Versión del servidor: 8.0.18
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `control_turnos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dpi` varchar(20) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `estado` enum('Activo','Inactivo') DEFAULT 'Activo',
  `modo_inactivo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `puesto` varchar(100) DEFAULT NULL,
  `turno` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dpi` (`dpi`),
  UNIQUE KEY `usuario` (`usuario`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `dpi`, `nombre`, `usuario`, `area`, `correo`, `password`, `estado`, `modo_inactivo`, `puesto`, `turno`) VALUES
(1, NULL, NULL, 'admin', NULL, NULL, '1234', 'Activo', NULL, NULL, NULL),
(5, '3045656440114', 'jose', 'jos', 'proyectos', 'proyectos@gmail.com', '2324', 'Activo', NULL, NULL, NULL),
(6, '3045656441212', 'rasa', 'rous', 'proyectos', 'proyectos@gmail.com', '8989', 'Activo', NULL, NULL, NULL),
(7, '30456527y46', 'josue', 'josue01', 'proyectos', 'proyectos@gmail.com', '0987', 'Activo', NULL, NULL, NULL),
(8, '30456522', 'Angel', 'Angel01', 'proyectos', 'proyectos@gmail.com', '9090', 'Activo', NULL, 'digitador', NULL),
(10, '304565644', 'ana', 'ana34', 'proyectos', 'proyectos@gmail.com', '2222', 'Activo', NULL, 'Digitador', NULL),
(11, '30456569090', 'ana', 'ana32', 'proyectos', 'proyectos@gmail.com', '222', 'Inactivo', NULL, 'Digitador', NULL),
(12, '304565690908989', 'ana', 'ana22', 'proyectos', 'proyectos@gmail.com', '9999', 'Activo', 'Permiso Personal', 'Digitador', 'Matutino'),
(13, '3045656909', 'ana', 'an33', 'proyectos', 'proyectos@gmail.com', '4444', 'Inactivo', 'Permiso Personal', 'Digitador', 'Matutino'),
(14, '3045656909788', 'alejandra', 'an34', 'proyectos', 'proyectos@gmail.com', '6666', 'Activo', NULL, 'Digitador', 'Matutino');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
