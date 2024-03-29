
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Clientes</title>
    </head>
    <body>

        <div class="d-flex">
        <div class="col-sm-8">
            
            <table class="table table-hover">
                <thead>
                    <tr>
                        <%--<th>ID</th>--%>
                        <th>DNI</th>
                        <th>NOMBRES</th>
                        <th>DIRECCION</th>
                        <th>ESTADO</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cl" items="${clientes}" > <%-- Es el nombre del atributo con el cual estamos enviando dede el controlador --%>
                        <tr>
                            <%--<td>${cl.getId()}</td> Se usa la variable em para poder listar --%>
                            <td>${cl.getDni()}</td>
                            <td>${cl.getNom()}</td>
                            <td>${cl.getDir()}</td>
                            <td>${cl.getEstadoId()}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=Cliente&accion=Editar&id=${cl.getId()}">Editar</a> <%-- Se captura el ID de la fila seleccionada --%>
                                <a class="btn btn-danger" href="Controlador?menu=Cliente&accion=Delete&id=${cl.getId()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

          <div class="col-sm-8">
            <div class="card col-sm-6">
                <div class="card-body" >
                   
                    <form action="Controlador?menu=Cliente" method="POST">
                        <div class="form-group">
                            <label>Dni</label>
                            <input type="number" value="${cliente.getDni()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${cliente.getNom()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Direccion</label>
                            <input type="text" value="${cliente.getDir()}" name="txtDir" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <!-- <input type="number" value="${cliente.getEstadoId()}" name="txtEstado" class="form-control">-->
                            <select class="form-control" name="txtEstado" value="${cliente.getEstadoId()}" id="inputGroupSelect01">
                                <option selected>Seleccione el nivel de acceso</option>
                                <option value="1">Habilitado</option>
                                <option value="2">Deshabilitado</option>
                            </select>
                        </div>
                        <br><br>
                        <label><h6>Agregar cliente nuevo:</h6></label>
                        <br>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <br><br>
                        <label><h6>Actualizar cliente modificado:</h6></label>
                        <br>
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>

        </div>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>

