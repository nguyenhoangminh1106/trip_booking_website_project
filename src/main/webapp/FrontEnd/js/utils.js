function re_enterPasswordMatch() {
    var confirmPassword = document.forms["signupForm"]["confirmPassword"].value;
    var password = document.forms["signupForm"]["password"].value;

    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    return true;
}