$(document).ready(function() {
    // Pie Chart
    var ctx1 = document.getElementById('pieChart').getContext('2d');
    new Chart(ctx1, {
        type: 'pie',
        data: {
            labels: ['Food','Housing','Shopping','Entertainment','Transport'],
            datasets: [{
                data: [4500,8000,3500,2000,1500],
                backgroundColor: ['#FF6384','#36A2EB','#FFCE56','#4BC0C0','#9966FF']
            }]
        }
    });
    
    // Bar Chart
    var ctx2 = document.getElementById('barChart').getContext('2d');
    new Chart(ctx2, {
        type: 'bar',
        data: {
            labels: ['Jan','Feb','Mar','Apr','May','Jun'],
            datasets: [
                { label: 'Income', data: [40000,42000,45000,48000,50000,52000], backgroundColor: 'green' },
                { label: 'Expense', data: [30000,32000,28000,35000,33000,38000], backgroundColor: 'red' }
            ]
        },
        options: { responsive: true }
    });
});