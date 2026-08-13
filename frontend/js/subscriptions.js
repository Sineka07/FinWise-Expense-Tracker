$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    if (!userId) window.location.href = 'login.html';
    loadSubscriptions();
    
    $('#subscriptionForm').on('submit', function(e) {
        e.preventDefault();
        var sub = {
            serviceName: $('#serviceName').val(),
            amount: parseFloat($('#amount').val()),
            renewalDate: $('#renewalDate').val()
        };
        $.ajax({
            url: 'http://localhost:8080/api/subscriptions/add?userId=' + userId,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(sub),
            success: function(res) { if(res.success) { alert('Added!'); loadSubscriptions(); } }
        });
    });
});

function loadSubscriptions() {
    var sample = [{serviceName:'Netflix',amount:649,renewalDate:'2026-08-15'},{serviceName:'Spotify',amount:119,renewalDate:'2026-07-20'}];
    var html = '';
    sample.forEach(function(s) {
        var days = Math.floor((new Date(s.renewalDate) - new Date()) / (1000*60*60*24));
        html += '<tr><td>'+s.serviceName+'</td><td>₹'+s.amount+'</td><td>'+s.renewalDate+'</td><td><span class="badge bg-warning">'+days+' days</span></td></tr>';
    });
    $('#subscriptionBody').html(html);
}