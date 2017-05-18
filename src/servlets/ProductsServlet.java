package servlets;

import models.ProductBean;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user01 on 18.05.2017.
 */
public class ProductsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> tableData = request.getParameterMap();
        ArrayList<ProductBean> products = new ArrayList<>();
        for (int i=0; i<tableData.size()/4; i++) {
            ProductBean product = new ProductBean();
            products.add(product);
        }

        int index = 0;
        int count = 0;
        for (Map.Entry<String, String[]> entry : tableData.entrySet())
        {
            ProductBean product = products.get(index);
            switch (count)
            {
                case 0:
                    product.setName(entry.getValue()[0]);
                    count++;
                    break;
                case 1:
                    product.setDescription(entry.getValue()[0]);
                    count++;
                    break;
                case 2:
                    product.setImageAddress(entry.getValue()[0]);
                    count++;
                    break;
                case 3:
                    product.setPrice(Integer.parseInt(entry.getValue()[0]));
                    index++;
                    count = 0;
                    break;
            }
        }

        ProductService service = new ProductService();
        try {
            if (service.putAll(products)) {
                response.getWriter().write("Saved.");
            }

            else {
                response.getWriter().write("Something going wrong. Try later.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//зачем эта функция?
    }
}
