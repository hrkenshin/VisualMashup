<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page import="java.io.*" %>
<%
  PrintWriter pw = new PrintWriter(trace);
  exception.printStackTrace(pw);
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
      Error Page
    </title>
  </head>
  <body>
    <div id="message">
      <h3>
        <font color="red">
          ${exception.message}
        </font>
      </h3>
    </div>
    <div id="stacktrace" style="display:block;">
      <%= trace.toString() %>
    </div>
  </body>
</html>