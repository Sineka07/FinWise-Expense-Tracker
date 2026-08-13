$(document).ready(function() {
    $('#registerForm').on('submit', function(e) {
        e.preventDefault();
        
        var fullName = $('#fullName').val().trim();
        var email = $('#email').val().trim();
        var password = $('#password').val().trim();
        var confirmPassword = $('#confirmPassword').val().trim();
        
        if (fullName === '' || email === '' || password === '') {
            alert('Please fill all fields');
            return;
        }
        
        if (password !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }
        
        $.ajax({
            url: 'http://localhost:8080/api/auth/register',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                fullName: fullName,
                email: email,
                password: password
            }),
            success: function(response) {
                if (response.success) {
                    alert('Registration successful! Please login.');
                    window.location.href = 'login.html';
                } else {
                    alert(response.message || 'Registration failed');
                }
            },
            error: function() {
                alert('Server error. Please try again.');
            }
        });
    });
});