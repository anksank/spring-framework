<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ankit.util.Mappings" %>
<html>
<head>
    <title>${toDoItem.title}</title>
</head>
<body>
    <div align="center">
        <table>
            <tr>
                <td><label>ID</label></td>
                <td>
                    <c:out value="${toDoItem.id}"/>
                </td>
            </tr>
            <tr>
                <td><label>Title</label></td>
                <td>
                    <c:out value="${toDoItem.title}"/>
                </td>
            </tr>
            <tr>
                <td><label>Deadline</label></td>
                <td>
                    <c:out value="${toDoItem.deadline}"/>
                </td>
            </tr>
            <tr>
                <td><label>Details</label></td>
                <td>
                    <c:out value="${toDoItem.details}"/>
                </td>
            </tr>
        </table>
        <c:url var="tableUrl" value="${Mappings.ITEMS}"/>
        <a href="${tableUrl}">Show Table</a>
    </div>
</body>
</html>