USE [master]
GO
/****** Object:  Database [clientes]    Script Date: 8/12/2021 03:32:37 ******/
CREATE DATABASE [clientes]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'clientes', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\clientes.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'clientes_log', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\clientes_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [clientes] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [clientes].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [clientes] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [clientes] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [clientes] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [clientes] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [clientes] SET ARITHABORT OFF 
GO
ALTER DATABASE [clientes] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [clientes] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [clientes] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [clientes] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [clientes] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [clientes] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [clientes] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [clientes] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [clientes] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [clientes] SET  DISABLE_BROKER 
GO
ALTER DATABASE [clientes] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [clientes] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [clientes] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [clientes] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [clientes] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [clientes] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [clientes] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [clientes] SET RECOVERY FULL 
GO
ALTER DATABASE [clientes] SET  MULTI_USER 
GO
ALTER DATABASE [clientes] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [clientes] SET DB_CHAINING OFF 
GO
ALTER DATABASE [clientes] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [clientes] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [clientes] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [clientes] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'clientes', N'ON'
GO
ALTER DATABASE [clientes] SET QUERY_STORE = OFF
GO
USE [clientes]
GO
/****** Object:  Table [dbo].[clientes]    Script Date: 8/12/2021 03:32:37 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[clientes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[pais] [varchar](50) NOT NULL,
	[direccion] [varchar](50) NOT NULL,
	[telefono] [int] NOT NULL,
	[tipo] [char](1) NOT NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[clientes] ON 
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (8, N'Maria ', N'Alemania', N'Capital', 82832938, N'F')
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (9, N'Sancho Aleman', N'Spain', N'Madrid', 77897877, N'-')
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (3, N'Anibal', N'Costa Rica', N'Alajuela', 71698121, N'P')
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (10, N'', N'', N'', 0, N'P')
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (11, N'Carmen Valle Murillo', N'Mexico', N'Guadalajara', 210218932, N'P')
GO
INSERT [dbo].[clientes] ([id], [nombre], [pais], [direccion], [telefono], [tipo]) VALUES (6, N'carlo', N'portugal', N'madrid', 9087878, N'-')
GO
SET IDENTITY_INSERT [dbo].[clientes] OFF
GO
USE [master]
GO
ALTER DATABASE [clientes] SET  READ_WRITE 
GO
