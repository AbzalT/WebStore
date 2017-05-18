<%--
  Created by IntelliJ IDEA.
  User: user01
  Date: 18.05.2017
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Интернет-магазин</title>
    <link rel="stylesheet" type="text/css" href="nabar.css">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <style>
        h4{
            font-weight: 600;
        }
        p{
            font-size: 12px;
            margin-top: 5px;
        }
        .price{
            font-size: 30px;
            margin: 0 auto;
            color: #333;
        }
        .right{
            float:right;
            border-bottom: 2px solid #4B8E4B;
        }
        .thumbnail{
            opacity:0.70;
            -webkit-transition: all 0.5s;
            transition: all 0.5s;
        }
        .thumbnail:hover{
            opacity:1.00;
            box-shadow: 0px 0px 10px #4bc6ff;
        }
        .line{
            margin-bottom: 5px;
        }
        @media screen and (max-width: 770px) {
            .right{
                float:left;
                width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <%
            String url="jdbc:postgresql://localhost:5432/WebStore";
            String user="postgres";
            String password="aq1sw2de3";
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(url,user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while(resultSet.next()){
        %>
        <div class="col-md-3 col-sm-6">
                <span class="thumbnail">
                    <img src="<%= resultSet.getString(5) %>" alt="...">
                    <h4><%= resultSet.getString(1) %></h4>
                    <div class="ratings">
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star"></span>
                        <span class="glyphicon glyphicon-star-empty"></span>
                    </div>
                    <p><%= resultSet.getString(3) %></p>
                    <hr class="line">
                    <div class="row">
                        <div class="col-md-6 col-sm-6">
                            <p class="price"><%= resultSet.getString(4) %> тг</p>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <form action="order" method="post">
                                <input type="hidden" value="<%= resultSet.getString(2) %>" name="productid" id="productid">
                                <button type="submit" class="btn btn-success right"> BUY ITEM</button>
                            </form>
                        </div>

                    </div>
                </span>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>
