package servlets;

import models.OrderBean;
import models.UserBean;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by user01 on 18.05.2017.
 */
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        Object userInSession = session.getAttribute("currentSessionUser");
        if (userInSession==null) {
            response.sendRedirect("login.jsp");
            return;
        }

        UUID userId =  ((UserBean)userInSession).getId();

        UUID productId = UUID.fromString(request.getParameter("productid"));

        OrderBean orderBean = new OrderBean( userId, productId);

        OrderService service = new OrderService();

        try {
            if(service.createOrder(orderBean)) {
                response.sendRedirect("productAdded.jsp");
            }
            else{
                response.getWriter().write("Something going wrong. Try later.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}