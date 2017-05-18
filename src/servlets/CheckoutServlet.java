package servlets;

import models.UserBean;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by user01 on 18.05.2017.
 */
@WebServlet(name = "CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Удаление всех товаров. Вроде бы как купили.
        HttpSession session = request.getSession(true);
        Object userInSession = session.getAttribute("currentSessionUser");

        UUID userId =  ((UserBean)userInSession).getId();

        OrderService service = new OrderService();
        if (service.acceptCheckout(userId)){
            response.getWriter().write("Thanks for your choice!");
        }
        else{
            response.getWriter().write("Something going wrong. Try later.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}