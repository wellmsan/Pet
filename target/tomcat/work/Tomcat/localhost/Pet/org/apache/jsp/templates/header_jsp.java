/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-04-25 01:08:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.templates;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<nav class=\"navbar-default navbar-static-side\" role=\"navigation\">\n");
      out.write("\t<div class=\"sidebar-collapse\">\n");
      out.write("\t\t<ul class=\"nav metismenu\" id=\"side-menu\">\n");
      out.write("\t\t\t<li class=\"nav-header\">\n");
      out.write("\t\t\t\t<div class=\"dropdown profile-element\">\n");
      out.write("\t\t\t\t\t<img alt=\"image\" class=\"rounded-circle\" src=\"img/profile_small.jpg\" />\n");
      out.write("\t\t\t\t\t<a data-toggle=\"dropdown\" class=\"dropdown-toggle\" href=\"#\"> <span\n");
      out.write("\t\t\t\t\t\tclass=\"block m-t-xs font-bold\">David Williams</span> <span\n");
      out.write("\t\t\t\t\t\tclass=\"text-muted text-xs block\">Art Director <b\n");
      out.write("\t\t\t\t\t\t\tclass=\"caret\"></b></span>\n");
      out.write("\t\t\t\t\t</a>\n");
      out.write("\t\t\t\t\t<ul class=\"dropdown-menu animated fadeInRight m-t-xs\">\n");
      out.write("\t\t\t\t\t\t<li><a class=\"dropdown-item\" href=\"profile.html\">Profile</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a class=\"dropdown-item\" href=\"contacts.html\">Contacts</a></li>\n");
      out.write("\t\t\t\t\t\t<li><a class=\"dropdown-item\" href=\"mailbox.html\">Mailbox</a></li>\n");
      out.write("\t\t\t\t\t\t<li class=\"dropdown-divider\"></li>\n");
      out.write("\t\t\t\t\t\t<li><a class=\"dropdown-item\" href=\"login.html\">Logout</a></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"logo-element\">Pet+</div>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"index.html\"><i class=\"fa fa-paw\"></i> <span class=\"nav-label\">Pets</span></a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"./controller?command=FornecedorIndex\"><i class=\"fa fa-truck\"></i> <span class=\"nav-label\">Fornecedores</span></a>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li>\n");
      out.write("\t\t\t\t<a href=\"./controller?command=UsuarioIndex\"><i class=\"fa fa-users\"></i> <span class=\"nav-label\">Usuários</span></a> \n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t</ul>\n");
      out.write("\n");
      out.write("\t</div>\n");
      out.write("</nav>\n");
      out.write("\n");
      out.write("<div id=\"page-wrapper\" class=\"gray-bg\">\n");
      out.write("\t<div class=\"row border-bottom\">\n");
      out.write("\t\t<nav class=\"navbar navbar-static-top  \" role=\"navigation\"\n");
      out.write("\t\t\tstyle=\"margin-bottom: 0\">\n");
      out.write("\t\t\t<div class=\"navbar-header\">\n");
      out.write("\t\t\t\t<a class=\"navbar-minimalize minimalize-styl-2 btn btn-primary \"\n");
      out.write("\t\t\t\t\thref=\"#\"><i class=\"fa fa-bars\"></i> </a>\n");
      out.write("\t\t\t\t<form role=\"search\" class=\"navbar-form-custom\"\n");
      out.write("\t\t\t\t\taction=\"search_results.html\">\n");
      out.write("\t\t\t\t\t<div class=\"form-group\">\n");
      out.write("\t\t\t\t\t\t<input type=\"text\" placeholder=\"Search for something...\" class=\"form-control\" name=\"top-search\" id=\"top-search\">\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t<ul class=\"nav navbar-top-links navbar-right\">\n");
      out.write("\t\t\t\t<li><a href=\"login.html\"> <i class=\"fa fa-sign-out\"></i>\n");
      out.write("\t\t\t\t\t\tSair\n");
      out.write("\t\t\t\t</a></li>\n");
      out.write("\t\t\t</ul>\n");
      out.write("\n");
      out.write("\t\t</nav>\n");
      out.write("\t</div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
