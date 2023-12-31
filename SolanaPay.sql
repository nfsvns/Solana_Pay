USE [master]
GO
/****** Object:  Database [PayPalDB]    Script Date: 11/26/2023 12:37:50 PM ******/
CREATE DATABASE [PayPalDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PayPalDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\PayPalDB.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PayPalDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\PayPalDB_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PayPalDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PayPalDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PayPalDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PayPalDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PayPalDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PayPalDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PayPalDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [PayPalDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PayPalDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PayPalDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PayPalDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PayPalDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PayPalDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PayPalDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PayPalDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PayPalDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PayPalDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PayPalDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PayPalDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PayPalDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PayPalDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PayPalDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PayPalDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PayPalDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PayPalDB] SET RECOVERY FULL 
GO
ALTER DATABASE [PayPalDB] SET  MULTI_USER 
GO
ALTER DATABASE [PayPalDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PayPalDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PayPalDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PayPalDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PayPalDB] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'PayPalDB', N'ON'
GO
USE [PayPalDB]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Accounts](
	[Username] [nvarchar](255) NOT NULL,
	[Password] [nvarchar](255) NOT NULL,
	[Email] [varchar](50) NULL,
	[Fullname] [nvarchar](50) NULL,
	[Photo] [nvarchar](50) NULL,
	[wallet_key] [nvarchar](max) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Authorities](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](255) NOT NULL,
	[roleId] [varchar](10) NULL,
 CONSTRAINT [PK__Authorit__3213E83FD6DA4BAB] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[BankAccounts]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BankAccounts](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](255) NOT NULL,
	[AccountNumber] [nvarchar](255) NOT NULL,
	[BankName] [nvarchar](255) NOT NULL,
	[AccountType] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK__BankAcco__349DA586D321C89E] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[comments]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comments](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[create_date] [date] NOT NULL,
	[product_id] [int] NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[is_liked] [bit] NULL,
 CONSTRAINT [PK__comments__3213E83F3072B847] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[products]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[price] [float] NOT NULL,
	[image] [nvarchar](50) NOT NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[replys]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[replys](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[create_date] [date] NOT NULL,
	[product_id] [int] NOT NULL,
	[comment_id] [int] NOT NULL,
	[username] [nvarchar](255) NOT NULL,
	[is_liked] [bit] NULL,
 CONSTRAINT [PK__replys__3213E83FB82ABEAA] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Roles]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Roles](
	[id] [varchar](10) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Transactions]    Script Date: 11/26/2023 12:37:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transactions](
	[TransactionID] [int] IDENTITY(1,1) NOT NULL,
	[Username] [nvarchar](255) NOT NULL,
	[TransactionType] [nvarchar](255) NOT NULL,
	[TransactionDate] [date] NOT NULL,
	[TransactionDetails] [nvarchar](max) NULL,
 CONSTRAINT [PK__Transact__55433A4B56450AEC] PRIMARY KEY CLUSTERED 
(
	[TransactionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
INSERT [dbo].[Accounts] ([Username], [Password], [Email], [Fullname], [Photo], [wallet_key]) VALUES (N'hoan', N'456', N'hau@gmail.com', N'ahsdasd', N'hieu.jpg', N'sgdjhsahjdsa')
INSERT [dbo].[Accounts] ([Username], [Password], [Email], [Fullname], [Photo], [wallet_key]) VALUES (N'huynh', N'456', N'huynh@gmail.com', N'hhunh', N'hieu.jpg', NULL)
INSERT [dbo].[Accounts] ([Username], [Password], [Email], [Fullname], [Photo], [wallet_key]) VALUES (N'NV01', N'123', N'nguyenpham242003@gmail.com', N'Phạm Đăng Nguyên', N'hieu.jpg', N'23')
INSERT [dbo].[Accounts] ([Username], [Password], [Email], [Fullname], [Photo], [wallet_key]) VALUES (N'NV02', N'123', N'haupss233@gmail.com', N'Nguyễn Thanh Hậu', N'hieu.jpg', N'12312312321')
INSERT [dbo].[Accounts] ([Username], [Password], [Email], [Fullname], [Photo], [wallet_key]) VALUES (N'nv03', N'789', N'nv03@gmail.com', N'abc', N'hieu.jpg', NULL)
SET IDENTITY_INSERT [dbo].[Authorities] ON 

INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (1, N'NV01', N'DIRE')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (3, N'NV02', N'STAF')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (4, N'NV03', N'CUST')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (9, N'hoan', N'DIRE')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (10, N'huynh', N'STAF')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (11, N'nv03', N'CUST')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (13, N'nv03', N'CUST')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (16, N'nv03', N'DIRE')
INSERT [dbo].[Authorities] ([id], [Username], [roleId]) VALUES (17, N'nv03', N'STAF')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
SET IDENTITY_INSERT [dbo].[comments] ON 

INSERT [dbo].[comments] ([id], [description], [create_date], [product_id], [username], [is_liked]) VALUES (24, N'sp hay lam', CAST(N'2023-11-24' AS Date), 2, N'nv03', NULL)
INSERT [dbo].[comments] ([id], [description], [create_date], [product_id], [username], [is_liked]) VALUES (25, N'y nghĩa lắm', CAST(N'2023-11-26' AS Date), 1, N'NV01', NULL)
SET IDENTITY_INSERT [dbo].[comments] OFF
SET IDENTITY_INSERT [dbo].[products] ON 

INSERT [dbo].[products] ([id], [name], [price], [image], [quantity]) VALUES (1, N'Product01', 0.1, N'product1.webp', 10)
INSERT [dbo].[products] ([id], [name], [price], [image], [quantity]) VALUES (2, N'Product01', 0.1, N'product1.webp', 10)
INSERT [dbo].[products] ([id], [name], [price], [image], [quantity]) VALUES (3, N'Product01', 0.1, N'f3ab6072.jpg', 10)
INSERT [dbo].[products] ([id], [name], [price], [image], [quantity]) VALUES (4, N'Product01', 0.1, N'ba42c633.jpg', 10)
INSERT [dbo].[products] ([id], [name], [price], [image], [quantity]) VALUES (5, N'Product2', 123, N'73a3f5c8.jpg', 11)
SET IDENTITY_INSERT [dbo].[products] OFF
SET IDENTITY_INSERT [dbo].[replys] ON 

INSERT [dbo].[replys] ([id], [description], [create_date], [product_id], [comment_id], [username], [is_liked]) VALUES (8, N'cam on ban ', CAST(N'2023-11-24' AS Date), 2, 24, N'nv02', NULL)
INSERT [dbo].[replys] ([id], [description], [create_date], [product_id], [comment_id], [username], [is_liked]) VALUES (9, N'123', CAST(N'2023-11-26' AS Date), 1, 25, N'NV01', NULL)
SET IDENTITY_INSERT [dbo].[replys] OFF
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'CUST', N'Customers')
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'DIRE', N'Directors')
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'STAF', N'Staffs')
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_Authorities_Roles] FOREIGN KEY([roleId])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_Authorities_Roles]
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD  CONSTRAINT [FK_Authorities_User] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Authorities] CHECK CONSTRAINT [FK_Authorities_User]
GO
ALTER TABLE [dbo].[BankAccounts]  WITH CHECK ADD  CONSTRAINT [FK_BankAccount_User] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[BankAccounts] CHECK CONSTRAINT [FK_BankAccount_User]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK_comments_Accounts] FOREIGN KEY([username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK_comments_Accounts]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK_comments_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK_comments_products]
GO
ALTER TABLE [dbo].[replys]  WITH CHECK ADD  CONSTRAINT [FK_replys_Accounts] FOREIGN KEY([username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[replys] CHECK CONSTRAINT [FK_replys_Accounts]
GO
ALTER TABLE [dbo].[replys]  WITH CHECK ADD  CONSTRAINT [FK_replys_comments] FOREIGN KEY([comment_id])
REFERENCES [dbo].[comments] ([id])
GO
ALTER TABLE [dbo].[replys] CHECK CONSTRAINT [FK_replys_comments]
GO
ALTER TABLE [dbo].[replys]  WITH CHECK ADD  CONSTRAINT [FK_replys_products] FOREIGN KEY([product_id])
REFERENCES [dbo].[products] ([id])
GO
ALTER TABLE [dbo].[replys] CHECK CONSTRAINT [FK_replys_products]
GO
ALTER TABLE [dbo].[Transactions]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_User] FOREIGN KEY([Username])
REFERENCES [dbo].[Accounts] ([Username])
GO
ALTER TABLE [dbo].[Transactions] CHECK CONSTRAINT [FK_Transaction_User]
GO
USE [master]
GO
ALTER DATABASE [PayPalDB] SET  READ_WRITE 
GO
