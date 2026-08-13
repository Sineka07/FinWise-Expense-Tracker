$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    if (!userId) window.location.href = 'login.html';
    
    $('#splitForm').on('submit', function(e) {
        e.preventDefault();
        var total = parseFloat($('#totalAmount').val());
        var people = parseInt($('#people').val());
        $('#perPerson').text((total/people).toFixed(2));
    });
});