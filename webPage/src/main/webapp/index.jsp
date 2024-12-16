<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Prevent Caching --%>
<% 
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>

    
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Web Page</title>
  <link rel="stylesheet" href="style.css">
  
   
  
</head>
<body>
  <div class="container">
    <!-- Login Form -->
    <div id="login-form" class="form-container">
      <h2>Login</h2>
      <form action="login" method="post" autocomplete="off">
        <label for="role">Role:</label>
        <select name="role" id="role" required>
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>
        <input type="email" name="mail" placeholder="E-mail" required autocomplete="off">
        <input type="password" name="password" placeholder="Password" required autocomplete="off">
        <button type="submit">Login</button>
      </form>
      <p>Don't have an account? <a href="#" onclick="switchForm('sign-up')">Sign Up</a></p>
    </div>

    <!-- Sign Up Form -->
    <div id="sign-up-form" class="form-container" style="display: none;">
      <h2>Sign Up</h2>
      <form action="signup" method="post">
        <label for="role">Role:</label>
        <select name="role" id="role" required>
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>
        <input type="email" name="mail" placeholder="E-Mail" required>
        <input type="text" name="username" placeholder="Username" required>
        <input type="password" name="spassword" placeholder="Password" required>
        <input type="password" name="confirmPassword" placeholder="Confirm Password" required>
        <input type="text" name="company" placeholder="Company" required>
      <!--   <input type="date" name="dob" placeholder="Date of Birth" required> --> 
        <button type="submit">Sign Up</button>
      </form>
      <p>Already have an account? <a href="#" onclick="switchForm('login')">Login</a></p>
    </div>
  </div>

  <!-- JSP-Specific Features -->
  <%-- Include JavaScript for form switching --%>
  <script src="switchForm.js"></script>
</body>
</html>
