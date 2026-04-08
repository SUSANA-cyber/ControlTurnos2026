<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <!-- BOOTSTRAP -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            height: 100vh;
            margin: 0;

            /* 🤍 FONDO BLANCO */
            background-color: white;

            /* 🖼️ IMAGEN */
            background-image: url('<%= request.getContextPath() %>/123.png');
            background-repeat: no-repeat;
            background-position: center;
            background-size: contain;
        }

        .contenedor {
            height: 100vh;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            padding-right: 80px;
        }

        /* 🔥 CAJA MÁS GRANDE + NEÓN */
        .card {
            width: 500px;
            border-radius: 20px;

            background: rgba(0, 0, 0, 0.7);
            backdrop-filter: blur(10px);

            border: 2px solid #00bfff;

            box-shadow:
                0 0 10px #00bfff,
                0 0 20px #00bfff,
                0 0 40px rgba(0,191,255,0.6);
        }

        /* 🔵 TITULO NEÓN CON FONDO */
        .titulo {
            color: #00bfff;

            text-shadow: 
                0 0 5px #00bfff,
                0 0 10px #00bfff,
                0 0 20px #00bfff,
                0 0 40px #00bfff;

            font-weight: bold;
            text-align: right;
            margin-bottom: 20px;
            font-size: 36px;
            margin-right: 10px;

            /* 🔥 FONDO SOLO PARA EL TEXTO */
            background: rgba(0,0,0,0.6);
            padding: 10px;
            border-radius: 10px;
        }

        /* INPUTS */
        .form-control {
            background: rgba(255,255,255,0.1);
            color: white;
            border: 1px solid #00bfff;
        }

        .form-control:focus {
            box-shadow: 0 0 10px #00bfff;
            border-color: #00bfff;
        }

        label {
            color: #ddd;
        }

        /* BOTÓN */
        .btn-primary {
            background-color: transparent;
            border: 2px solid #00bfff;
            color: #00bfff;
            font-weight: bold;
        }

        .btn-primary:hover {
            background-color: #00bfff;
            color: black;
            box-shadow: 0 0 15px #00bfff;
        }
    </style>
</head>

<body>

<div class="contenedor">

    <div>
        <!-- TITULO -->
        <h2 class="titulo">Sistema Control de Turnos</h2>

        <div class="card shadow-lg p-4">

            <h4 class="text-center text-light mb-3">Inicio de Sesión</h4>

            <!-- FORM ORIGINAL -->
            <form action="LoginServlet" method="post">
                
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" name="usuario" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" name="password" class="form-control" required>
                </div>

                <div class="d-grid">
                    <input type="submit" value="Ingresar" class="btn btn-primary">
                </div>

            </form>

            <!-- ERROR -->
            <div class="mt-3 text-center">
            <%
                String error = request.getParameter("error");
                if (error != null) {
            %>
                <div class="alert alert-danger">
                    Credenciales incorrectas
                </div>
            <%
                }
            %>
            </div>

        </div>
    </div>

</div>

</body>
</html>