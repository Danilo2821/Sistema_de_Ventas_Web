package Controlador;

import Modelo.User;
import Modelo.UserDAO;
import config.Encriptador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Validar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    
    User us = new User(); //Se instancia la entidad cliente
    UserDAO udao = new UserDAO(); //Se instancia la clase ClienteDAO que tiene los metodos que estan relacionados con la BD
    Encriptador encriptador = new Encriptador();
    String usuario = "Iniciar Sesion";
    String password = "Iniciar Sesion";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario);
        session.setAttribute("password", password);
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       String accion=request.getParameter("accion");
       if(accion.equalsIgnoreCase("Ingresar")){
           String user=request.getParameter("txtuser");
           String pass=request.getParameter("txtpass");
           us=udao.validar(user, pass);
           if(us != null && us.getUser()!=null){
               usuario = us.getUser();
               password = us.getPass();      
               request.setAttribute("usuario", us);
               request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
           }else{
          request.getRequestDispatcher("index.jsp").forward(request, response); 
       }
    }else if(accion.equalsIgnoreCase("Salir")){
            HttpSession session = request.getSession();
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response); 
       }
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
