<%@ page import="models.UserBean" %>
<!-- Second navbar for sign in -->
<nav class="navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Web Store</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar-collapse-2">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="products.jsp">Home</a></li>
        <li><a href="#">About</a></li>
        <% UserBean user = (UserBean)(session.getAttribute("currentSessionUser"));
          if(user==null) {out.write("" +
                  "<li>\n" +
                  "          <a class=\"btn btn-default btn-outline btn-circle collapsed\" href=\"login.jsp\" aria-expanded=\"false\" aria-controls=\"nav-collapse2\">Sign in</a>\n" +
                  "        </li>\n" +
                  "        <li>\n" +
                  "          <a class=\"btn btn-default btn-outline btn-circle collapsed\" href=\"signup.jsp\" aria-expanded=\"false\" aria-controls=\"nav-collapse2\">Sign up</a>\n" +
                  "        </li>");}
          else {
            out.print(" <li><a href=\"cart.jsp\">Cart</a></li>" +
                    "<li><a href=\"login?out=true\">Sign out</a></li>");
          }
        %>


      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container -->
</nav><!-- /.navbar -->