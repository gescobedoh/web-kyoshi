package com.kyoshi.controlador;

import com.kyoshi.dao.ProductoDao;
import com.kyoshi.dao.ProductoImpl;
import com.kyoshi.entidades.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imagen/*")
public class ImagenServlet extends HttpServlet {
    private ProductoDao dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dao = new ProductoImpl();
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        Producto pro = new Producto();
        pro = dao.ListarId(id);
        if(pro!=null){
            if(pro.getFotoProducto()!=null&&pro.getFotoProducto().length>0){
                resp.setContentType("image/jpg");
                resp.setContentLength(pro.getFotoProducto().length);
                resp.getOutputStream().write(pro.getFotoProducto());
            }
        }
    }
    
}
