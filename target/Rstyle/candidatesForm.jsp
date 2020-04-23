<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Candidates Application</title>
    <style>
        select {
            width: 250px;
        }
    </style>
</head>
<body>
<center>
    <h1>Candidates</h1>
</center>
<div align="center">

    <form action="getInfo" method="GET">
        <select name="name">>
            <c:forEach var="item" items="${requestScope.candidateList}">
                <option><c:out value='${item}' /></option>
            </c:forEach>
        </select>
        <input type="submit" value="get information"></p>
    </form>

    <h3>contacts</h3>
    <table width="400" border="1" cellpadding="5">
        <tr>
            <td>profession</td>
            <td><c:out value="${requestScope.profession}" /></td>
        </tr>
        <tr>
            <td>phone</td>
            <td><c:out value="${requestScope.phone}" /></td>
        </tr>
        <tr>
            <td>email</td>
            <td><c:out value="${requestScope.email}" /></td>
        </tr>
        <tr>
            <td>repository</td>
            <td><c:out value="${requestScope.repository}" /></td>
        </tr>
        <tr>
            <td>region</td>
            <td><c:out value="${requestScope.region}" /></td>
        </tr>
    </table>

    <h3>education</h3>
    <table width="400" border="1" cellpadding="5">
        <tr>
            <td>university</td>
            <td><c:out value="${requestScope.universityTitle}" /></td>
        </tr>
        <tr>
            <td>faculty</td>
            <td><c:out value="${requestScope.faculty}" /></td>
        </tr>
    </table>

    <h3>experience</h3>
    <table width="400" border="1" cellpadding="5">
        <tr>
            <td>company</td>
            <td><c:out value="${requestScope.company}" /></td>
        </tr>
        <tr>
            <td>time</td>
            <td><c:out value="${requestScope.time}" /></td>
        </tr>
    </table>

    <h3>skills</h3>
    <table width="400" border="1" cellpadding="5">
        <c:forEach var="item" items="${requestScope.skillsList}">
            <tr>
                <td><c:out value="${item}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>