function validateFullName(fullName) {
    if (fullName === "") {
        alert("Please enter a valid full name");
        return false;
    }
    return true;
}

function validateEmail(email) {
    var emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if (!email.match(emailPattern)) {
        alert("Please enter a valid email");
        return false;
    }
    return true;
}

function validatePhoneNumber(phoneNumber) {
    var phonePattern = /^[0-9]{10,11}$/;
    if (!phoneNumber.match(phonePattern)) {
        alert("Please enter a valid phone number");
        return false;
    }
    return true;
}

function validateAddress(address) {
    if (address === "") {
        alert("Please enter a valid address");
        return false;
    }
    return true;
}

function validateAccount(account) {
    if (account === "") {
        alert("Please enter a valid account");
        return false;
    }
    return true;
}

function validatePassword(password) {
    if (password === "") {
        alert("Please enter a valid password");
        return false;
    }
    return true;
}

function validateForm() {
    var fullName = document.getElementById('fullName');
    var email = document.getElementById('email');
    var phoneNumber = document.getElementById('phoneNumber');
    var address = document.getElementById('address');
    var account = document.getElementById('account');
    var password = document.getElementById('password');

    return validateFullName(fullName.value) && validateEmail(email.value) && validatePhoneNumber(phoneNumber.value) && validateAddress(address.value) && validateAccount(account.value) && validatePassword(password.value);
}

function validateTripName(tripName) {
    if (tripName === "") {
        alert("Please enter a valid trip name");
        return false;
    }
    return true;
}

function validatePrice(price) {
    if (price === "") {
        alert("Please enter a valid price");
        return false;
    }
    return true;
}

function validateImg_url(img_url) {
    if (img_url === "") {
        alert("Please enter a valid link");
        return false;
    }
    return true;
}

function validateDesc_url(desc_url) {
    if (desc_url === "") {
        alert("Please enter a valid link");
        return false;
    }
    return true;
}

function validateDates() {
            var startDate = new Date(document.getElementById('startDate').value);
            var endDate = new Date(document.getElementById('endDate').value);
			
			if (!startDate || !endDate ) {
				alert("Please enter a valid date");
				return false;
			}
            else if (endDate <= startDate) {
                alert('End Date must be after the Start Date.');
                return false;
            }
            else {
				return true;
			}
        }
        
function validateTripForm() {
	var tripName = document.getElementById('tripName');
    var price = document.getElementById('price');
    var img_url = document.getElementById('img_url');
    var desc_url = document.getElementById('desc_url');
    var address = document.getElementById('address');

    return validateTripName(tripName.value) && validatePrice(price.value) && validateImg_url(img_url.value) && validateDesc_url(desc_url.value) && validateAddress(address.value) && validateDates();
}

