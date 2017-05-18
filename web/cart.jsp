<%@ page import="java.sql.*" %>
<%@ page import="java.util.UUID" %><%--
  Created by IntelliJ IDEA.
  User: volt
  Date: 31.03.2017
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="index.jsp" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" type="text/css" href="nabar.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Product</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th class="text-center">Price</th>
                    <th> </th>
                </tr>
                </thead>
                <tbody>
                <%
                    Object userInSession = session.getAttribute("currentSessionUser");
                    UUID userId =  ((UserBean)userInSession).getId();

                    Driver driver = new org.postgresql.Driver();
                    DriverManager.registerDriver(driver);
                    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StoreDb","postgres", "postgres");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT books.book_name, " +
                            "books.book_author, books.book_price, books.image_address FROM orders, books WHERE " +
                            "user_id='" +userId+ "' AND orders.book_id=books.id");
                    while(resultSet.next()){
                %>
                <tr>
                    <td class="col-sm-8 col-md-6">
                        <div class="media">
                            <a class="thumbnail pull-left" href="#"> <img class="media-object" src="<%= resultSet.getString(4) %>" style="width: 72px; height: 72px;"> </a>
                            <div class="media-body">
                                <h4 class="media-heading"><a href="#"><%= resultSet.getString(1) %></a></h4>
                                <h5 class="media-heading"> by <a href="#"><%= resultSet.getString(2) %></a></h5>
                                <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                            </div>
                        </div></td>
                    <td>   </td>
                    <td>   </td>
                    <td class="col-sm-1 col-md-1 text-center"><strong><%= resultSet.getString(3) %> тг</strong></td>
                    <td class="col-sm-1 col-md-1">
                        <button type="button" class="btn btn-danger">
                            <span class="glyphicon glyphicon-remove"></span> Remove
                        </button></td>
                </tr>
                <% } %>
                <tr>
                    <td>   </td>
                    <td>   </td>
                    <td>   </td>
                    <td>
                        <form action="${pageContext.request.contextPath}/" method="post">
                            <button type="submit" class="btn btn-default">
                                <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="checkout" method="post">
                            <button type="submit" class="btn btn-success">
                                Checkout <span class="glyphicon glyphicon-play"></span>
                            </button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
