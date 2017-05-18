<%@ page import="services.ProductService" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {
            var counter = 0;

            $("#addproduct").on("click", function () {
                var newRow = $("<tr>");
                var cols = "";

                cols += '<td><input type="text" class="form-control" name="name' + counter + '"/></td>';
                cols += '<td><input type="text" class="form-control" name="description' + counter + '"/></td>';
                cols += '<td><input type="text" class="form-control" name="imageaddress' + counter + '"/></td>';
                cols += '<td><input type="number" class="form-control" name="price' + counter + '"/></td>';

                cols += '<td><input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete"></td>';
                newRow.append(cols);
                $("table.order-list").append(newRow);
                counter++;
            });

            $("form").submit(function (event) { // function to process submitted table
                    var tableData = []; // we will store rows' data into this array
                    $("#productTable") // select table by id
                        .each(function () { // for each selected row extract data
                            var tableRow = {};
                            var jRow = $(this);
                            tableRow.product = {name: jRow.find('input.name').value(),
                                description: jRow.find('input.description').value(),
                                url: jRow.find('input.imageaddress').value(),
                                price: jRow.find('input.price').value()};


                            tableData.push(tableRow);
                        });

                    $.post(
                        "products", /*url of consuming servlet*/
                        {tableData: tableData}, /*data*/
                        function () {
                            alert("Success!");
                        }, /*function to execute in case of success*/
                        "json" /* data type */
                    );
                    event.preventDefault(); //Prevent sending form by browser
                }
            );

            $("table.order-list").on("click", ".ibtnDel", function (event) {
                $(this).closest("tr").remove();
                counter -= 1
            });


        });
    </script>
</head>
<body>

<div class="container">
    <form id="save_form" action="products" method="post">
        <table id="productTable" class=" table order-list">
            <thead>
            <tr>
                <td>Name</td>
                <td>Description</td>
                <td>Image Address</td>
                <td>Price</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <%
                    Driver driver = new org.postgresql.Driver();
                    DriverManager.registerDriver(driver);
                    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/StoreDb","postgres", "postgres");
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
                    while(resultSet.next()){
                %>

                <td class="col-sm-3">
                    <input type="text" name="name" class="form-control productName" value="<%= resultSet.getString(1) %>" />
                </td>
                <td class="col-sm-2">
                    <input type="text" name="description"  class="form-control productDescription" value="<%= resultSet.getString(3) %>"/>
                </td>
                <td class="col-sm-4">
                    <input type="text" name="imageaddress"  class="form-control productImage" value="<%= resultSet.getString(5) %>"/>
                </td>
                <td class="col-sm-2">
                    <input type="number" name="price"  class="form-control productPrice" value="<%= resultSet.getString(4) %>"/>
                </td>
                <td>
                    <input type="button" class="ibtnDel btn btn-md btn-danger "  value="Delete">
                </td>
            </tr>
            <% } %>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5" style="text-align: left;">
                    <input type="button" class="btn btn-lg btn-block " id="addproduct" value="Add Product" />
                </td>
            </tr>
            <tr>
                <td colspan="5" style="text-align: left;">
                    <input type="submit" class="btn btn-lg btn-block " id="save" value="Save All" form="save_form"/>
                </td>
            </tr>
            </tfoot>
        </table>
    </form>
</div>
</body>
</html>
