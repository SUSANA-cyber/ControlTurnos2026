<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Menú Principal</title>

    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            height: 100vh;
            margin: 0;

            /* 🤍 FONDO BLANCO */
            background-color: white;

            /* 🖼️ NUEVA IMAGEN */
            background-image: url('<%= request.getContextPath() %>/456.png');
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;

            display: flex;
            justify-content: flex-end; /* 👉 MOVER A LA DERECHA */
            align-items: center;
            padding-right: 100px; /* 👉 separación del borde */
        }

        /* 🔥 CAJA NEÓN */
        .menu-box {
            width: 500px;
            padding: 40px;
            border-radius: 20px;

            background: rgba(0, 0, 0, 0.7);
            backdrop-filter: blur(10px);

            border: 2px solid #00bfff;

            box-shadow:
                0 0 10px #00bfff,
                0 0 20px #00bfff,
                0 0 40px rgba(0,191,255,0.6);

            text-align: center;
        }

        /* 🔵 TITULO */
        h1 {
            color: #00bfff;

            text-shadow: 
                0 0 5px #00bfff,
                0 0 10px #00bfff,
                0 0 20px #00bfff;

            margin-bottom: 30px;
        }

        /* 🔘 BOTONES */
        .menu-btn {
            display: block;
            width: 100%;
            margin: 10px 0;
            padding: 12px;

            text-decoration: none;
            font-weight: bold;
            color: #00bfff;

            border: 2px solid #00bfff;
            border-radius: 10px;

            transition: 0.3s;
        }

        .menu-btn:hover {
            background-color: #00bfff;
            color: black;
            box-shadow: 0 0 15px #00bfff;
        }

        /* 🔴 BOTÓN CERRAR */
        .logout {
            border-color: red;
            color: red;
        }

        .logout:hover {
            background-color: red;
            color: white;
            box-shadow: 0 0 15px red;
        }
    </style>
</head>

<body>

<div class="menu-box">

    <h1>Menú Principal</h1>

    <a href="usuarios.jsp" class="menu-btn">Mantenimiento de Usuarios</a>
    <a href="turnos.jsp" class="menu-btn">Asignación de Turnos</a>
    <a href="marcaje.jsp" class="menu-btn">Marcaje</a>
    <a href="solicitudes.jsp" class="menu-btn">Solicitudes</a>

    <a href="login.jsp" class="menu-btn logout">Cerrar sesión</a>

</div>

</body>
</html>