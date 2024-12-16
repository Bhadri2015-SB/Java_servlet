/**
 * 
 */
function switchForm(form) {
  const loginForm = document.getElementById("login-form");
  const signUpForm = document.getElementById("sign-up-form");

  if (form === "sign-up") {
    loginForm.style.display = "none";
    signUpForm.style.display = "block";
  } else {
    loginForm.style.display = "block";
    signUpForm.style.display = "none";
  }
}

// switchForm.js

//function validatePassword(spassword) {
  // Simplified regex: 8-16 characters, at least one uppercase, one lowercase, and one digit
//  const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d]{8,16}$/;
//  return passwordPattern.test(spassword);
//}
function validatePassword(password) {
  // Check password length between 8 and 16 characters
  if (password.length < 8 || password.length > 16) {
    return false;
  }

  // Check if password contains at least one uppercase letter
  if (!/[A-Z]/.test(password)) {
    return false;
  }

  // Check if password contains at least one lowercase letter
  if (!/[a-z]/.test(password)) {
    return false;
  }

  // Check if password contains at least one digit
  if (!/\d/.test(password)) {
    return false;
  }
  
  if (!/[!@#$%^&*()]/.test(password)) {
      return false;
    }

  // If all conditions are met, return true
  return true;
}


// Event listener for Sign Up form to validate password before submission
document.getElementById("sign-up-form").addEventListener("submit", function(event) {
  const spassword = document.querySelector("input[name='spassword']").value;
  const confirmPassword = document.querySelector("input[name='confirmPassword']").value;

  // Debugging: log password values to check if they are being retrieved correctly
 // console.log("Password:", spassword);
 // console.log("Confirm Password:", confirmPassword);

  if (!validatePassword(spassword)) {
    event.preventDefault(); // Prevent form submission
    alert("Password must be 8-16 characters long, with at least one uppercase letter, one lowercase letter, and one digit.");
  } else if (spassword !== confirmPassword) {
    event.preventDefault(); // Prevent form submission
    alert("Passwords do not match!");
  }
});

