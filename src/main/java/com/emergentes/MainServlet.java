
package com.emergentes;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author quisp
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String op = request.getParameter("op");
        
        if(op.equals("vaciar")){
            //vaciar carrito
            HttpSession ses = request.getSession();
            ses.invalidate();
            response.sendRedirect("index.jsp");
        }
        if(op.equals("eliminar")){
            int pos = -1;
            int buscado = 0;
            int id = Integer.parseInt(request.getParameter("id"));
            
            HttpSession ses = request.getSession();
            ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("lista");
            
            for(Producto p: lista){
                pos++;
                if(p.getId() == id){
                    buscado = pos; 
                }
            }
            lista.remove(buscado);
            response.sendRedirect("index.jsp");
        }
        
        if(op.equals("check")){
         
        
            int id = Integer.parseInt(request.getParameter("id"));
            
            HttpSession ses = request.getSession();
            ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("lista");
            
            for(Producto p: lista){
       
                if(p.getId() == id){
                    if(p.isCompletado()){
                        p.setCompletado(false);
                    }else{
                        p.setCompletado(true);
                    }
                   
                }
            }
            response.sendRedirect("index.jsp");
            
        }
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
       
        int id = Integer.parseInt( request.getParameter("id"));
        String tarea = request.getParameter("tarea");
  
        Producto prod = new Producto();
        
        prod.setId(id);
        prod.setTarea(tarea);
        prod.setCompletado(false);
        
        HttpSession ses = request.getSession();
        
        ArrayList<Producto> lista = (ArrayList<Producto>)ses.getAttribute("lista");
        
        lista.add(prod);
        
        response.sendRedirect("index.jsp");
    }

}
