$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    if (!userId) window.location.href = 'login.html';
    loadGoals();
    
    $('#goalForm').on('submit', function(e) {
        e.preventDefault();
        var goal = {
            goalName: $('#goalName').val(),
            targetAmount: parseFloat($('#targetAmount').val()),
            targetDate: $('#targetDate').val()
        };
        $.ajax({
            url: 'http://localhost:8080/api/goals/add?userId=' + userId,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(goal),
            success: function(res) { if(res.success) { alert('Goal added!'); loadGoals(); } }
        });
    });
});

function loadGoals() {
    var userId = sessionStorage.getItem('userId');
    $.ajax({
        url: 'http://localhost:8080/api/goals/user/' + userId,
        type: 'GET',
        success: function(res) {
            var html = '<div class="row">';
            var sample = [{goalName:'Vacation',targetAmount:50000,savedAmount:20000,targetDate:'2026-12-31'}];
            sample.forEach(function(g) {
                var pct = Math.round((g.savedAmount/g.targetAmount)*100);
                html += '<div class="col-md-6 mb-3"><div class="card"><div class="card-body"><h5>'+g.goalName+'</h5><p>Target: ₹'+g.targetAmount+'</p><div class="progress"><div class="progress-bar" style="width:'+pct+'%">'+pct+'%</div></div></div></div></div>';
            });
            html += '</div>';
            $('#goalList').html(html);
        }
    });
}