
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Ventas</title>
    </head>
    <body>
        <div class="col-sm-8" >
            <br>
            <label id="titulo" ><h4>Registro de ventas: </h4></label>
            <br><br>
           
            <table id="tabla" class="table table-hover">
                <thead>
                    <tr>
                        <th>ID VENTAS</th>
                        <th>ID CLIENTES</th>
                        <th>ID USUARIO</th>
                        <th>NUMERO SERIE</th>
                        <th>FECHA</th>
                        <th>MONTO</th>
                        <th>ESTADO</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="ven" items="${ventas}" > <%-- Es el nombre del atributo con el cual estamos enviando dede el controlador --%>
                        <tr>
                            <td>${ven.getId()}</td>
                            <td>${ven.getIdcliente()}</td>
                            <td>${ven.getIdusuario()}</td>
                            <td>${ven.getNumserie()}</td>
                            <td>${ven.getFecha()}</td>
                            <td>${ven.getMonto()}</td>
                            <td>${ven.getEstado()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
           
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
