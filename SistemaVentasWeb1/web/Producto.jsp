
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Productos</title>
    </head>
    <body>
       
        <div class="d-flex">
        <div class="col-sm-8">
           
            <table id="tabla" class="table table-hover">
                <thead>
                    <tr>
                        <%-- <th>ID</th>--%>
                        <th>NOMBRES</th>
                        <th>PRECIO</th>
                        <th>STOCK</th>
                        <th>ESTADO</th>
                        <th>DESCRIPCION</th>
                        <th>ACCIONES</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pro" items="${productos}" > <%-- Es el nombre del atributo con el cual estamos enviando dede el controlador --%>
                        <tr>
                            <%--<td>${pro.getId()}</td> Se usa la variable em para poder listar --%>
                            <td>${pro.getNom()}</td>
                            <td>${pro.getPrecio()}</td>
                            <td>${pro.getStock()}</td>
                            <td>${pro.getEstado()}</td>
                            <td>${pro.getDescripcion()}</td>
                            <td>
                                <a class="btn btn-warning" href="Controlador?menu=Producto&accion=Editar&id=${pro.getId()}">Editar</a> <%-- Se captura el ID de la fila seleccionada --%>
                                <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Delete&id=${pro.getId()}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-sm-8">
            <div class="card col-sm-6">
                <div class="card-body" >
                    <form id="formulario" action="Controlador?menu=Producto" method="POST">
                        <div class="form-group">
                            <label>Nombres</label>
                            <input type="text" value="${producto.getNom()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Precio</label>
                            <input type="number" step="any" value="${producto.getPrecio()}" name="txtPrecio" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="number" type="number" value="${producto.getStock()}" name="txtStock" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" value="${producto.getEstado()}" name="txtEstado" class="form-control">
                           <%-- <select class="form-control" name="txtEstado" value="${producto.getEstado()}" id="inputGroupSelect01">
                                <option selected>Seleccione el estado del producto</option>
                                <option value="1">Disponible</option>
                                <option value="2">No disponible</option>
                            </select>--%>
                        </div>
                        <div class="form-group">
                            <label>Descripcion</label>
                            <input type="text" value="${producto.getDescripcion()}" name="txtDesc" class="form-control">
                        </div>
                        <br><br>
                        <label><h6>Agregar producto nuevo:</h6></label>
                        <br>
                        <input id="input" type="submit" name="accion" value="Agregar" class="btn btn-info">
                        <br><br>
                        <label><h6>Actualizar producto modificado:</h6></label>
                        <br>
                        <input id="input" type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>
            </div>
        </div>
                        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-eMNCOe7tC1doHpGoWe/6oMVemdAVTMs2xqW4mwXrXsW0L84Iytr2wi5v2QjrP/xp" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js" integrity="sha384-cn7l7gDp0eyniUwwAZgrzD06kc/tftFf19TOAs2zVinnD/C7E91j9yyk5//jjpt/" crossorigin="anonymous"></script>
    </body>
</html>
