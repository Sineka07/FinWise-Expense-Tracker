$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    if (!userId) window.location.href = 'login.html';
    
    loadBudgets();
    
    $('#budgetForm').on('submit', function(e) {
        e.preventDefault();
        var budget = {
            category: $('#category').val(),
            amount: parseFloat($('#amount').val()),
            monthYear: '2026-07'
        };
        $.ajax({
            url: 'http://localhost:8080/api/budget/set?userId=' + userId,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(budget),
            success: function(res) {
                if (res.success) { alert('Budget set!'); loadBudgets(); }
            }
        });
    });
});

function loadBudgets() {
    var userId = sessionStorage.getItem('userId');
    $.ajax({
        url: 'http://localhost:8080/api/budget/user/' + userId + '/2026-07',
        type: 'GET',
        success: function(res) {
            var html = '';
            var sample = [{category:'Food',amount:5000,spent:2500},{category:'Housing',amount:8000,spent:8000}];
            sample.forEach(function(b) {
                var pct = Math.round((b.spent/b.amount)*100);
                var color = pct > 90 ? 'danger' : pct > 70 ? 'warning' : 'success';
                html += '<tr><td>'+b.category+'</td><td>₹'+b.amount+'</td><td>₹'+b.spent+'</td><td><span class="badge bg-'+color+'">'+pct+'%</span></td></tr>';
            });
            $('#budgetBody').html(html);
        }
    });
}