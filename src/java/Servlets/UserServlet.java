/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author prabh
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/users.jsp");
        String edit = req.getParameter("edit");
        String delete = req.getParameter("delete");
        String email = req.getParameter("email");
        
        try {
            List<User> users = (new UserService()).getAll();
            req.setAttribute("users", users);
            
            List<Role> roles = (new RoleService()).getAll();
            req.setAttribute("roles", roles);
            
            if(edit != null && email != null && !email.equals("")) {
                User user = (new UserService()).get(email);
                req.setAttribute("user", user);
            }
            
            if(delete != null && email != null && !email.equals("")) {
                (new UserService()).delete(email);
                req.setAttribute("users", null);
                resp.sendRedirect("");
                return;
            }
            
            rd.forward(req, resp);
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //resp.sendError(500);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/users.jsp");
        String action = req.getParameter("_action");
        String email = req.getParameter("email");
        String active = req.getParameter("active");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        
        try {
            if(action != null && action.equals("create")) {
                (new UserService()).insert(email, active == null ? 0 : 1, firstName, lastName, password, Integer.parseInt(role));
                resp.sendRedirect("");
                return;
            }
            if(action != null && action.equals("edit")) {
                (new UserService()).update(email, active == null ? 0 : 1, firstName, lastName, password, Integer.parseInt(role));
                resp.sendRedirect("");
                return;
            }
            resp.sendRedirect("");
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
