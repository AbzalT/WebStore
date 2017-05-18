package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user01 on 18.05.2017.
 */
public class MainServlet extends HttpServlet {
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestDispatcher RequestDispatcherObj = request.getRequestDispatcher("/products.jsp");
        try {
            RequestDispatcherObj.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
