<%-- 
    Document   : index
    Created on : Apr 30, 2011, 8:14:59 PM
    Author     : bento
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%   
        response.sendRedirect(request.getContextPath() + "/public/home.html");
%>
