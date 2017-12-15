<%@ page pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

    <head>

        <meta charset="utf-8" />

        <title>Liste des inscrits</title>

        <link type="text/css" rel="stylesheet" href="form.css" />

    </head>

    <body>
	Liste des inscrits:
       <table border=1>
       <tr>
       		<td> nom </td>
       		<td> email </td>
       		<td> date d'inscription </td>
       </tr>
       <c:forEach items="${ listeinscrits }" var="inscrit" varStatus="status">
       		<tr>
       			<td>
       		
    				<c:out value="${ inscrit.nom }" /> 
    			</td>
    			<td>
    	
    				<c:out value="${ inscrit.email }" /> 
    			</td>
    			
    			<td>
    	
    				<c:out value="${ inscrit.dateInscription }" /> 
    			</td>
      		 </tr>
	   </c:forEach>
       
       
       </table>

    </body>

</html>