package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.User;
import Modelo.UserDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import config.Fecha;
import config.Encriptador;
import config.GenerarSerie;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Producto pro = new Producto(); //Se instancia la entidad producto
    ProductoDAO pdao = new ProductoDAO(); //Se instancia la clase ProductoDAO que tiene los metodos que estan relacionados con la BD
    int idp;

    Cliente cl = new Cliente(); //Se instancia la entidad cliente
    ClienteDAO cdao = new ClienteDAO(); //Se instancia la clase ClienteDAO que tiene los metodos que estan relacionados con la BD
    int idc;

    User us = new User(); //Se instancia la entidad cliente
    UserDAO udao = new UserDAO(); //Se instancia la clase ClienteDAO que tiene los metodos que estan relacionados con la BD
    int idu;

    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;

    String numeroserie;
    VentaDAO vdao = new VentaDAO();
    int idv;

    Fecha fechaSistem = new Fecha();

    List<Venta> ventas = new ArrayList<>();
    int idcliente;
    int idempleado;
    String Numserie;
    String fecha;
    double monto;
    String estado;
    char[] autoridad;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        int idUsuarioLogin = 0;
        
        String idUsuarioParam = request.getParameter("id");
        User usuario = (User) request.getAttribute("usuario");
        int idRol = 0;
        if (usuario != null && usuario.getRolId() >= 0) {
            idRol = usuario.getRolId();
            idUsuarioLogin =  usuario.getId();
        } else if (idUsuarioParam != null) {
            int idInt = Integer.parseInt(idUsuarioParam);
            idRol = udao.getRolId(idInt);
            idUsuarioLogin = idInt;
        }
        if (idRol != 0) {
            autoridad = udao.getAutoridad(idRol).toCharArray();
        } 

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp?id=" + idUsuarioLogin).forward(request, response);
        }
        if (menu.equals("Usuario")) {
            if (autoridad[0] == '1') {
                switch (accion) {
                   
                    case "Listar":
                        List users = udao.listar();//esta variable va a recibir los datos que tiene el objeto empleado
                        //Una vez que esta variable lista almacene los datos que tiene el objeto empleado, se envian al formulario 
                        request.setAttribute("usuarios", users);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                        String nom = request.getParameter("txtNombres");
                        String tel = request.getParameter("txtTel");
                        String dir = request.getParameter("txtDir");
                        int est = Integer.parseInt(request.getParameter("txtEstado"));
                        String user = request.getParameter("txtUsuario");
                        String pass = request.getParameter("txtPass");
                        String passEncrypt = Encriptador.md5(pass);
                        int rol = Integer.parseInt(request.getParameter("txtRol"));
                        us.setDni(dni);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                        us.setNom(nom);//la var em hacer referencia a la entidad Empleado
                        us.setTel(tel);
                        us.setDir(dir);
                        us.setEstadoId(est);
                        us.setUser(user);
                        us.setPass(passEncrypt);
                        us.setRolId(rol);
                        udao.agregar(us);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                        //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                        request.getRequestDispatcher("alertas/UsuarioAgregado.jsp").forward(request, response);//se actualiza la tabla
                        break;

                    case "Editar":

                        idu = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada
                        User u = udao.listarId(idu);//se declara una variable del objeto empleado que va a ser igual a la clase EmpleadoDAO con el metodo listar por id, con el parametro del id que se ha seleccionado
                        request.setAttribute("usuario", u);//una vez que la variable e almacene los datos de la fila seleccionada, se envia al formulario
                        request.getRequestDispatcher("Controlador?menu=Usuario&accion=Listar").forward(request, response);//se actualiza la tabla
                        break;

                    case "Actualizar":

                        String dni1 = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                        String nom1 = request.getParameter("txtNombres");
                        String tel1 = request.getParameter("txtTel");
                        String dir1 = request.getParameter("txtDir");
                        int est1 = Integer.parseInt(request.getParameter("txtEstado"));
                        String user1 = request.getParameter("txtUsuario");
                        String pass1 = request.getParameter("txtPass");
                        String passEncrypt1 = Encriptador.md5(pass1);
                        int rol1 = Integer.parseInt(request.getParameter("txtRol"));
                        us.setDni(dni1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                        us.setNom(nom1);//la var em hacer referencia a la entidad Empleado
                        us.setTel(tel1);
                        us.setDir(dir1);
                        us.setEstadoId(est1);
                        us.setUser(user1);
                        us.setPass(passEncrypt1);
                        us.setRolId(rol1);
                        us.setId(idu);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                        udao.actualizar(us);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                        request.getRequestDispatcher("alertas/UsuarioActualizado.jsp").forward(request, response);//se actualiza la tabla
                        break;

                    case "Delete":

                        idu = Integer.parseInt(request.getParameter("id"));
                        udao.delete(idu);
                        request.getRequestDispatcher("alertas/UsuarioBorrado.jsp").forward(request, response);//se actualiza la tabla
                        break;

                    default:
                        throw new AssertionError();
                }
            }
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
        }
        if (menu.equals("Cliente")) {
            if (autoridad[1] == '1') {
                switch (accion) {
                    case "Listar":
                        List clientes = cdao.listar();//esta variable va a recibir los datos que tiene el objeto empleado
                        //Una vez que esta variable lista almacene los datos que tiene el objeto empleado, se envian al formulario 
                        request.setAttribute("clientes", clientes);
                        break;
                    case "Agregar":
                        String dni = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                        String nom = request.getParameter("txtNombres");
                        String dir = request.getParameter("txtDir");
                        int est = Integer.parseInt(request.getParameter("txtEstado"));
                        cl.setDni(dni);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                        cl.setNom(nom);//la var em hacer referencia a la entidad Empleado
                        cl.setDir(dir);
                        cl.setEstadoId(est);
                        cdao.agregar(cl);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                        //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                        request.getRequestDispatcher("alertas/ClienteAgregado.jsp").forward(request, response);//se actualiza la tabla
                        break;
                    case "Editar":
                        idc = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada
                        Cliente c = cdao.listarId(idc);//se declara una variable del objeto empleado que va a ser igual a la clase EmpleadoDAO con el metodo listar por id, con el parametro del id que se ha seleccionado
                        request.setAttribute("cliente", c);//una vez que la variable e almacene los datos de la fila seleccionada, se envia al formulario
                        request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//se actualiza la tabla
                        break;
                    case "Actualizar":
                        String dni1 = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                        String nom1 = request.getParameter("txtNombres");
                        String dir1 = request.getParameter("txtDir");
                        int est1 = Integer.parseInt(request.getParameter("txtEstado"));
                        cl.setDni(dni1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                        cl.setNom(nom1);//la var em hacer referencia a la entidad Empleado
                        cl.setDir(dir1);
                        cl.setEstadoId(est1);
                        cl.setId(idc);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                        cdao.actualizar(cl);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                        request.getRequestDispatcher("alertas/ClienteActualizado.jsp").forward(request, response);//se actualiza la tabla
                        break;
                    case "Delete":
                        idc = Integer.parseInt(request.getParameter("id"));
                        cdao.delete(idc);
                        request.getRequestDispatcher("alertas/ClienteBorrado.jsp").forward(request, response);//se actualiza la tabla
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);

        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List producto = pdao.listar();//esta variable va a recibir los datos que tiene el objeto empleado
                    //Una vez que esta variable lista almacene los datos que tiene el objeto empleado, se envian al formulario 
                    request.setAttribute("productos", producto);
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombres");// estas variables estan almacenando los datos ingresados por las cajas de texto
                    double precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String est = request.getParameter("txtEstado");
                    //String foto = request.getParameter("txtFoto");
                    String descripcion = request.getParameter("txtDesc");
                    pro.setNom(nom);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    pro.setPrecio(precio);//la var em hacer referencia a la entidad Empleado
                    pro.setStock(stock);
                    pro.setEstado(est);
                    pro.setDescripcion(descripcion);
                    pdao.agregar(pro);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                    //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                    request.getRequestDispatcher("alertas/ProductoAgregado.jsp").forward(request, response);//se actualiza la tabla
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada
                    Producto p = pdao.listarId(idp);//se declara una variable del objeto empleado que va a ser igual a la clase EmpleadoDAO con el metodo listar por id, con el parametro del id que se ha seleccionado
                    request.setAttribute("producto", p);//una vez que la variable e almacene los datos de la fila seleccionada, se envia al formulario
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Actualizar":
                    String nom1 = request.getParameter("txtNombres");// estas variables estan almacenando los datos ingresados por las cajas de texto
                    double precio1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String est1 = request.getParameter("txtEstado");
                    String descripcion1 = request.getParameter("txtDesc");
                    pro.setNom(nom1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    pro.setPrecio(precio1);//la var em hacer referencia a la entidad Empleado
                    pro.setStock(stock1);
                    pro.setEstado(est1);
                    pro.setDescripcion(descripcion1);
                    pro.setId(idp);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                    pdao.actualizar(pro);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                    request.getRequestDispatcher("alertas/ProductoActualizado.jsp").forward(request, response);//se actualiza la tabla
                    break;
                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("alertas/ProductoBorrado.jsp").forward(request, response);//se actualiza la tabla
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);

        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");//se recibe desde la caja de texto
                    cl.setDni(dni);//se envia el parametro a la clase ClienteDAO, para ejecutar el metodo buscar
                    cl = cdao.buscar(dni);//se realiza la busqueda en la BD
                    request.setAttribute("cliente", cl);//se pueda mostrar en el form
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarClienteNombre":
                    String nomb = request.getParameter("clientes");//se recibe desde la caja de texto
                    cl.setNom(nomb);//se envia el parametro a la clase ClienteDAO, para ejecutar el metodo buscar
                    cl = cdao.buscarNombreCliente(nomb);//se realiza la busqueda en la BD
                    request.setAttribute("cliente", cl);//se pueda mostrar en el form
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));//se captura el id del producto
                    pro = pdao.listarId(id);//la entidad producto pro va almacenar los datos que genera el metodo listan por id
                    request.setAttribute("cliente", cl);
                    request.setAttribute("producto", pro);//se envian los datos para que se muestren en el form
                    request.setAttribute("lista", lista);//se envian los datos al form
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "BuscarProductoNombre":
                    String nom = request.getParameter("nombres");//se captura el  del producto
                    pro = pdao.buscarNombre(nom);//la entidad producto pro va almacenar los datos que genera el metodo listan por id
                    request.setAttribute("cliente", cl);
                    request.setAttribute("producto", pro);//se envian los datos para que se muestren en el form
                    request.setAttribute("lista", lista);//se envian los datos al form
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    break;
                case "Agregar":
                    request.setAttribute("cliente", cl);
                    totalPagar = 0.0;//cada vez que se agrege un prod se inicializa en 0
                    item = item + 1;//se capturan los valores de las variables declaradas al principio
                    cod = pro.getId();
                    descripcion = request.getParameter("nombres");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);//el objeto venta v esta capturando todos los valores que ingresa el usuario por medio del form
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);
                    lista.add(v);//el objeto se agrega a la lista
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();//total a pagar va a ser igual a la sumatoria de la columna subtotal, recorre la lista por medio del la variable index i    
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);//se envia al form
                    request.setAttribute("nserie", numeroserie);
                    break;

                case "GenerarVenta":
                    //Actualizacion del Stock
                    for (int i = 0; i < lista.size(); i++) { //el bucle for recorre la cantidad de elementos que tiene agregado el comprobante de pago
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();// estas dos variables van a recibir los valores que tiene la tabla de ventas
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);//id del producto capturado en la lista
                        int sac = pr.getStock() - cantidad;//stock actual se le resta la cantidad recibida desde la tabla
                        aO.actualizarstock(idproducto, sac);//se envia el nuevo stock actualizado a la BD utilizando el metodo actualizarstock
                    }
                    //Guardar Venta
                    v.setIdcliente(cl.getId());
                    v.setIdusuario(us.getId());//el id emplado debe ser el codigo del usu que ingreso al sistema
                    v.setNumserie(numeroserie);
                    v.setFecha(fechaSistem.FechaBD());
                    v.setMonto(totalPagar);
                    v.setEstado("Cancelado");
                    vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());//se almacena el id de la venta que se ha guardado
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();//se inicializa en vacio
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());//se captura el id del prod que esta en la lista
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        vdao.guardarDetalleventas(v);//se guarda el detalle de ventas utilizando el metodo que esta en ventadao, el parametro v es la entidad de venta
                    }
                    request.getRequestDispatcher("alertas/VentaRegistrada.jsp").forward(request, response);
                    break;

                default:
                    v = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0.0;

                    numeroserie = vdao.GenerarSerie();//la var numero de serie almacena el maximo del num de serie que existe en la BD
                    if (numeroserie == null) {
                        numeroserie = "00000001";
                        request.setAttribute("nserie", numeroserie);//se envia al form
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);//se convierte el numero de serie que esta en tipo string
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);//la variable numeroserie es igual al resultado de enviar la variable convertida en entero en incremetar al metodo numeroserie que esta en la clase instanciada de generarserie gs
                        request.setAttribute("nserie", numeroserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }

        if (menu.equals("ListaVentas")) {
            switch (accion) {
                case "ListarVenta":
                    List ventas = vdao.listarVenta();
                    request.setAttribute("ventas", ventas);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("ListaVentas.jsp").forward(request, response);
        }

        if (menu.equals("VerProductos")) {
            switch (accion) {
                case "Listar":
                    List producto = pdao.listar();//esta variable va a recibir los datos que tiene el objeto empleado
                    //Una vez que esta variable lista almacene los datos que tiene el objeto empleado, se envian al formulario 
                    request.setAttribute("productos", producto);
                    break;
            }
            request.getRequestDispatcher("VerProductos.jsp").forward(request, response);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
