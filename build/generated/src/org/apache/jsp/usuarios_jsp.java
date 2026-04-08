package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class usuarios_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Usuarios</title>\n");
      out.write("\n");
      out.write("    <!-- BOOTSTRAP -->\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            background: linear-gradient(135deg, #15343f, #0f2027);\n");
      out.write("            min-height: 100vh;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card-box {\n");
      out.write("            width: 700px;\n");
      out.write("            background: rgba(0,0,0,0.9);\n");
      out.write("            padding: 30px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 0 30px #00bfff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            color: #00bfff;\n");
      out.write("            text-shadow: 0 0 15px #00bfff;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .section-title {\n");
      out.write("            margin-top: 15px;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            color: #00e6ff;\n");
      out.write("            font-weight: bold;\n");
      out.write("            border-bottom: 1px solid #00bfff;\n");
      out.write("            padding-bottom: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-size: 13px;\n");
      out.write("            color: #00e6ff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-control, select {\n");
      out.write("            background: rgba(255,255,255,0.1);\n");
      out.write("            border: 1px solid #00bfff;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-control:focus, select:focus {\n");
      out.write("            box-shadow: 0 0 10px #00bfff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-custom {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            width: 100%;\n");
      out.write("            background: transparent;\n");
      out.write("            border: 2px solid #00bfff;\n");
      out.write("            color: #00bfff;\n");
      out.write("            font-weight: bold;\n");
      out.write("            border-radius: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-custom:hover {\n");
      out.write("            background: #00bfff;\n");
      out.write("            color: black;\n");
      out.write("            box-shadow: 0 0 15px #00bfff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .alert {\n");
      out.write("            padding: 8px;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .link-back {\n");
      out.write("            display: block;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 10px;\n");
      out.write("            color: #00bfff;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"card-box\">\n");
      out.write("\n");
      out.write("<h2>Registrar Empleado</h2>\n");
      out.write("\n");
      out.write("<!-- MENSAJES -->\n");

if(request.getParameter("ok") != null){

      out.write("\n");
      out.write("    <div class=\"alert alert-success text-center\">\n");
      out.write("        ✔ Registrado con éxito\n");
      out.write("    </div>\n");

}
if(request.getParameter("error") != null){

      out.write("\n");
      out.write("    <div class=\"alert alert-danger text-center\">\n");
      out.write("        ⚠ Usuario ya existe\n");
      out.write("    </div>\n");

}

      out.write("\n");
      out.write("\n");
      out.write("<form action=\"UsuarioServlet\" method=\"post\">\n");
      out.write("\n");
      out.write("<div class=\"row\">\n");
      out.write("\n");
      out.write("    <!-- 🔵 DATOS PERSONALES -->\n");
      out.write("    <div class=\"col-md-6\">\n");
      out.write("        <div class=\"section-title\">Datos personales</div>\n");
      out.write("\n");
      out.write("        <label>DPI</label>\n");
      out.write("        <input type=\"text\" name=\"dpi\" class=\"form-control\" required>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Nombre</label>\n");
      out.write("        <input type=\"text\" name=\"nombre\" class=\"form-control\" required>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Usuario</label>\n");
      out.write("        <input type=\"text\" name=\"usuario\" class=\"form-control\" required>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Contraseña</label>\n");
      out.write("        <input type=\"password\" name=\"password\" class=\"form-control\" required>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!-- 🟢 DATOS LABORALES -->\n");
      out.write("    <div class=\"col-md-6\">\n");
      out.write("        <div class=\"section-title\">Datos laborales</div>\n");
      out.write("\n");
      out.write("        <label>Área</label>\n");
      out.write("        <input type=\"text\" name=\"area\" class=\"form-control\" required>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Puesto</label>\n");
      out.write("        <input type=\"text\" name=\"puesto\" class=\"form-control\" required>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Estado</label>\n");
      out.write("        <select name=\"estado\" class=\"form-control\">\n");
      out.write("            <option>Activo</option>\n");
      out.write("            <option>Inactivo</option>\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("        <label class=\"mt-2\">Correo</label>\n");
      out.write("        <input type=\"text\" name=\"correo\" class=\"form-control\" required>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<input type=\"submit\" value=\"Registrar\" class=\"btn btn-custom\">\n");
      out.write("\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<a href=\"dashboard.jsp\" class=\"link-back\">⬅ Regresar</a>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
