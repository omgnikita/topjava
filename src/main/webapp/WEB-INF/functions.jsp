<?xml version="1.0" encoding="UTF-8" ?>
<taglib
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-jsptaglibrary_2_1.xsd"
        version="2.1">

    <tlib-version>1.0</tlib-version>
    <short-name>Custom_Functions</short-name>
    <uri>http://example.com/functions</uri>

    <function>
        <name>formatLocalDateTime</name>
        <function-class>com.example.Dates</function-class>
        <function-signature>java.lang.String formatLocalDateTime(java.time.LocalDateTime, java.lang.String)</function-signature>
    </function>
</taglib>