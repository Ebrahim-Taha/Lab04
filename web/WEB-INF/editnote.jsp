
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple Note Keeper</title>
        
    </head>
    
    <body>
        
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        
        <div>
            <form action = "note" method = "post">
                <label>Title:</label>
                <input type ="text" name="title" value="${note.title}">
                <br>
                <label>Contents:</label>
                <textarea name="contents" rows="10" cols="25">${note.contents}</textarea>
                <br>
                <input type="submit" name="action" value="Save">
            </form>
        </div>
                
    </body>
    
</html>
