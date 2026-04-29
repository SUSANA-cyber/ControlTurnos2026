package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Login</title>\n");
      out.write("\n");
      out.write("    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            height: 100vh;\n");
      out.write("            margin: 0;\n");
      out.write("            background-color: white;\n");
      out.write("            background-image: url('");
      out.print( request.getContextPath() );
      out.write("/123.png');\n");
      out.write("            background-repeat: no-repeat;\n");
      out.write("            background-position: center;\n");
      out.write("            background-size: contain;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .contenedor {\n");
      out.write("            height: 100vh;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: flex-end;\n");
      out.write("            align-items: center;\n");
      out.write("            padding-right: 80px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card {\n");
      out.write("            width: 500px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            background: rgba(0, 0, 0, 0.7);\n");
      out.write("            backdrop-filter: blur(10px);\n");
      out.write("            border: 2px solid #00bfff;\n");
      out.write("            box-shadow: 0 0 20px rgba(0,191,255,0.6);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .titulo {\n");
      out.write("            color: #00bfff;\n");
      out.write("            text-align: right;\n");
      out.write("            font-weight: bold;\n");
      out.write("            font-size: 36px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            background: rgba(0,0,0,0.6);\n");
      out.write("            padding: 10px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-control {\n");
      out.write("            background: rgba(255,255,255,0.1);\n");
      out.write("            color: white;\n");
      out.write("            border: 1px solid #00bfff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-control:focus {\n");
      out.write("            box-shadow: 0 0 10px #00bfff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            color: #ddd;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-primary {\n");
      out.write("            background-color: transparent;\n");
      out.write("            border: 2px solid #00bfff;\n");
      out.write("            color: #00bfff;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-primary:hover {\n");
      out.write("            background-color: #00bfff;\n");
      out.write("            color: black;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"contenedor\">\n");
      out.write("\n");
      out.write("    <div>\n");
      out.write("        <h2 class=\"titulo\">Sistema Control de Turnos</h2>\n");
      out.write("\n");
      out.write("        <div class=\"card shadow-lg p-4\">\n");
      out.write("\n");
      out.write("            <h4 class=\"text-center text-light mb-3\">Inicio de Sesión</h4>\n");
      out.write("\n");
      out.write("            <form action=\"LoginServlet\" method=\"post\">\n");
      out.write("                \n");
      out.write("                <div class=\"mb-3\">\n");
      out.write("                    <label class=\"form-label\">Usuario</label>\n");
      out.write("                    <input type=\"text\" name=\"usuario\" class=\"form-control\" required>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"mb-3\">\n");
      out.write("                    <label class=\"form-label\">Contraseña</label>\n");
      out.write("                    <input type=\"password\" name=\"password\" class=\"form-control\" required>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"d-grid\">\n");
      out.write("                    <input type=\"submit\" value=\"Ingresar\" class=\"btn btn-primary\">\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("            <div class=\"mt-3 text-center\">\n");
      out.write("            ");

                String error = request.getParameter("error");
                if (error != null) {
            
      out.write("\n");
      out.write("                <div class=\"alert alert-danger\">\n");
      out.write("                    Credenciales incorrectas o usuario inactivo\n");
      out.write("                </div>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
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
