/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Danim
 */
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
    Empleado em = new Empleado(); //Se instancia la entidad empleado
    EmpleadoDAO edao = new EmpleadoDAO(); //Se instancia la clase EmpleadoDAO que tiene los metodos que estan relacionados con la BD
    int ide;
    
    Producto pro = new Producto(); //Se instancia la entidad empleado
    ProductoDAO pdao = new ProductoDAO(); //Se instancia la clase EmpleadoDAO que tiene los metodos que estan relacionados con la BD
    int idp;
    
    Cliente cl = new Cliente(); //Se instancia la entidad empleado
    ClienteDAO cdao = new ClienteDAO(); //Se instancia la clase EmpleadoDAO que tiene los metodos que estan relacionados con la BD
    int idc;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();//esta variable va a recibir los datos que tiene el objeto empleado
                    //Una vez que esta variable lista almacene los datos que tiene el objeto empleado, se envian al formulario 
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    em.setNom(nom);//la var em hacer referencia a la entidad Empleado
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                    //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada
                    Empleado e = edao.listarId(ide);//se declara una variable del objeto empleado que va a ser igual a la clase EmpleadoDAO con el metodo listar por id, con el parametro del id que se ha seleccionado
                    request.setAttribute("empleado", e);//una vez que la variable e almacene los datos de la fila seleccionada, se envia al formulario
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");// estas variables estan almacenando los datos ingresados por las cajas de texto
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    em.setDni(dni1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    em.setNom(nom1);//la var em hacer referencia a la entidad Empleado
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                    edao.actualizar(em);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                     request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Cliente")) {
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
                    String est = request.getParameter("txtEstado");
                    cl.setDni(dni);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    cl.setNom(nom);//la var em hacer referencia a la entidad Empleado
                    cl.setDir(dir);
                    cl.setEstado(est);
                    cdao.agregar(cl);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                    //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//se actualiza la tabla
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
                    String est1 = request.getParameter("txtEstado");
                    cl.setDni(dni1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    cl.setNom(nom1);//la var em hacer referencia a la entidad Empleado
                    cl.setDir(dir1);
                    cl.setEstado(est1);
                    cl.setId(idc);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                    cdao.actualizar(cl);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Delete":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                     request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                default:
                    throw new AssertionError();
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
                    pro.setNom(nom);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    pro.setPrecio(precio);//la var em hacer referencia a la entidad Empleado
                    pro.setStock(stock);
                    pro.setEstado(est);
                    pdao.agregar(pro);//se ejecuta el metodo agregar que esta en la clase EmpleadoDAO
                    //Una vez agregados los datos a la BD, se deben listar la tabla con los datos actualizados
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//se actualiza la tabla
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
                    pro.setNom(nom1);//esta variable y este metodo sirven para agregar dentro del objeto los nuevos valores
                    pro.setPrecio(precio1);//la var em hacer referencia a la entidad Empleado
                    pro.setStock(stock1);
                    pro.setEstado(est1);
                    pro.setId(idp);//el objeto empleado requiere que sea enviado el id de la fila seleccionada, como parametro tiene el id de la fila seleccionada, el cual es capturado al momento de presionar editar
                    pdao.actualizar(pro);//despues de que se envian todas las variables, se ejecuta el metodo actualizar que esta en la clase EmpleadoDAO, y como parametro pide el objeto empleado
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                     request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);//se actualiza la tabla
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
            
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion){
                case "BuscarCliente":
                    String dni=request.getParameter("codigocliente");//se recibe desde la caja de texto
                    cl.setDni(dni);//se envia el parametro a la clase ClienteDAO, para ejecutar el metodo buscar
                    cl=cdao.buscar(dni);//se realiza la busqueda en la BD
                    request.setAttribute("cliente", cl);//se pueda mostrar en el form
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
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
