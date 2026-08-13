$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();
        
        var email = $('#email').val().trim();
        var password = $('#password').val().trim();
        
        if (email === '' || password === '') {
            alert('Please fill all fields');
            return;
        }
        
        var loginData = {
            email: email,
            password: password
        };
        
        console.log('Sending login data:', loginData);
        
        $.ajax({
            url: 'http://localhost:8080/api/auth/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(loginData),
            success: function(response) {
                console.log('Response:', response);
                if (response.success) {
                    sessionStorage.setItem('userId', response.userId);
                    sessionStorage.setItem('userName', response.fullName);
                    alert('Login successful!');
                    window.location.href = 'dashboard.html';
                } else {
                    alert(response.message || 'Invalid credentials');
                }
            },
            error: function(xhr, status, error) {
                console.log('Error details:', xhr);
                console.log('Status:', status);
                console.log('Error:', error);
                
                if (xhr.status === 0) {
                    alert('Cannot connect to server!\nMake sure backend is running on http://localhost:8080');
                } else if (xhr.status === 404) {
                    alert('API not found!\nCheck URL: http://localhost:8080/api/auth/login');
                } else if (xhr.status === 500) {
                    alert('Server error!\nCheck backend console for details.');
                } else {
                    alert('Server Error (Status: ' + xhr.status + ')\nPlease check console for details.');
                }
            }
        });
    });
});