<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="css/main.css" type="text/css" rel="stylesheet" />
    <title>Login page</title>
  </head>
  <body>
    <form action="index.do" method="POST">
      <div class="form-group">
        <p>Library Login</p>

        <input type="text" name="login" id="login" placeholder="User Name" />

        <input
          type="password"
          name="passwd"
          id="passwd"
          placeholder="Password"
        />
        <button>LOG IN</button>
      </div>
    </form>
  </body>
</html>
