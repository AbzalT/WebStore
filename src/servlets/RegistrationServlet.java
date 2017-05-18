package servlets;

import models.UserBean;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by user01 on 18.05.2017.
 */
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("password_confirmation");

        if (!password.equals(passwordConfirmation)) {
            response.getWriter().write("Passwords are not match");
            return;
        }

        UserBean user = new UserBean();
        user.setLogin(request.getParameter("email"));
        user.setPassword(password);
        user.setName(request.getParameter("first_name"));
        user.setLastName(request.getParameter("last_name"));

        UserService service = new UserService();

        try {
            if (service.register(user)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
                response.sendRedirect("");
            }
            else {
                response.getWriter().write("Something wrong. Try later...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
