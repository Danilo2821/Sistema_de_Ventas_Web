
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Registrar Ventas</title>
        <style>
            @media print{
                .parte01, .btn, .accion{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-lg-5 parte01">
                <div class="card">
                    <form action="Controlador?menu=NuevaVenta" method="POST">
                        <div class="card-body">
                            <div class="form-group">
                                <label><h4>Datos del Cliente: </h4></label>
                                <br><br>
                            </div>
                            <div class="form-group d-flex-xl-column">
                                <div class="col-sm-6 d-flex">
                                    <label>DNI del Cliente:</label>
                                    <input type="text" name="codigocliente" value="${cliente.getDni()}" class="form-control" placeholder="Codigo">
                                    <button type="submit" name="accion" value="BuscarCliente" class="btn btn-outline-info">Buscar DNI</button>
                                </div>
                                <br><br>    
                                <div class="col-sm-6 d-flex">
                                    <label>Nombre del Cliente:</label>
                                    <input type="text" name="clientes" value="${cliente.getNom()}" placeholder="Datos Cliente" class="form-control">
                                    <button type="submit" name="accion" value="BuscarClienteNombre" class="btn btn-outline-info">Buscar Nombre</button>
                                </div>
                            </div>
                            <div class="form-group">
                                <br> <br>
                                <label><h4>Datos de los Productos: </h4></label>
                                <br><br>
                            </div>
                            <div class="form-group d-flex-xl-column">
                                <div class="col-sm-6 d-flex">
                                    <label>Nombre del Producto:</label>
                                    <input type="text" name="nombres" value="${producto.getNom()}" placeholder="Datos Producto" class="form-control">
                                    <button type="submit" name="accion" value="BuscarProductoNombre" class="btn btn-outline-info">Buscar Nombre</button>
                                </div>
                                <br><br> 
                            </div>
                            <div class="form-group d-flex-xl-column">
                                <div class="col-sm-6 col-sm-6">
                                    <label>Precio del Producto:</label>
                                    <input type="text" name="precio" value="${producto.getPrecio()}" class="form-control" placeholder="$/.0.00">
                                </div>
                                <div class="col-sm-6">
                                    <label>Cantidad:</label>
                                    <input type="number" value="1" name="cant" placeholder="" class="form-control">
                                </div>
                                <div class="col-sm-6">
                                    <label>Stock disponible del Producto:</label>
                                    <input type="text" name="stock" value="${producto.getStock()}" placeholder="Stock" class="form-control">
                                </div>
                            </div>
                            <br><br>    
                            <div class="form-group d-flex-column">
                                <button type="submit" name="accion" value="Agregar" class="btn btn-outline-info" >Agregar Producto</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
           
            <div class="col-sm-7">
                <div class="card parte02">
                    <div class="card-body">
                        <div class="d-flex ml-auto col-sm-6">
                            <label class="text-right mt-2 col-sm-6">NRO. SERIE: </label>
                            <input readonly="" type="text" name="numeroserie" value="${nserie}" class="form-control">
                        </div>
                        <br>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <%-- <th>N°</th>--%>
                                    <%--<th>CODIGO</th>--%>
                                    <th>PRODUCTO</th>
                                    <th>PRECIO</th>
                                    <th>CANTIDAD</th>
                                    <th>SUBTOTAL</th>
                                    <th class="accion">ACCION</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="list" items="${lista}">
                                    <tr>
                                        <%-- <td>${list.getItem()}</td>--%>
                                        <%-- <td>${list.getIdproducto()}</td>--%>
                                        <td>${list.getDescripcionP()}</td>
                                        <td>${list.getPrecio()}</td>
                                        <td>${list.getCantidad()}</td>
                                        <td>${list.getSubtotal()}</td>
                                        <td class="d-flex">
                                            <a class="btn btn-danger" href="Controlador?menu=NuevaVenta&accion=default">Borrar</a>      
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer d-flex">
                        <div class="col-sm-6">
                            <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" onclick="print()" class="btn btn-success">Generar Venta</a>
                            <a href="Controlador?menu=NuevaVenta&accion=default" class="btn btn-danger">Cancelar</a>
                        </div>
                        <div class="col-sm-3 ml-auto">
                            <input type="text" name="txtTotal" value="s/. ${totalpagar}0" class="form-control">
                        </div>
                    </div>
                </div>              
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
