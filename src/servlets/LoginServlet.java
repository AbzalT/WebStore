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
 * Created by user01 on 17.05.2017.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserBean user = new UserBean();
        user.setLogin(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        UserService service = new UserService();

        try {
            if ((user = service.logIn(user)) != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
                response.sendRedirect("");
            }
            else {
                response.sendRedirect("loginDenied.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute("currentSessionUser");
        response.sendRedirect("");
    }
}
