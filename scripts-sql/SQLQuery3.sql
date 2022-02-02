USE [master]
GO
/****** Object:  Database [partes]    Script Date: 8/12/2021 03:33:54 ******/
CREATE DATABASE [partes]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'partes', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\partes.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'partes_log', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\partes_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [partes] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [partes].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [partes] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [partes] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [partes] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [partes] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [partes] SET ARITHABORT OFF 
GO
ALTER DATABASE [partes] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [partes] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [partes] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [partes] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [partes] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [partes] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [partes] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [partes] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [partes] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [partes] SET  DISABLE_BROKER 
GO
ALTER DATABASE [partes] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [partes] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [partes] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [partes] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [partes] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [partes] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [partes] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [partes] SET RECOVERY FULL 
GO
ALTER DATABASE [partes] SET  MULTI_USER 
GO
ALTER DATABASE [partes] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [partes] SET DB_CHAINING OFF 
GO
ALTER DATABASE [partes] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [partes] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [partes] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [partes] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'partes', N'ON'
GO
ALTER DATABASE [partes] SET QUERY_STORE = OFF
GO
USE [partes]
GO
/****** Object:  Table [dbo].[partes]    Script Date: 8/12/2021 03:33:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[partes](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[numero] [varchar](50) NOT NULL,
	[tipo] [varchar](50) NOT NULL,
	[modelo] [int] NOT NULL,
	[precio] [float] NOT NULL,
	[estado] [varchar](50) NOT NULL
) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [partes] SET  READ_WRITE 
GO