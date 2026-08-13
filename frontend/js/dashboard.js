$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    var userName = sessionStorage.getItem('userName');
    
    if (!userId) {
        window.location.href = 'login.html';
    }
    
    $('#userName').text(userName || 'User');
    loadDashboard();
    
    $('#logoutBtn').on('click', function(e) {
        e.preventDefault();
        sessionStorage.clear();
        window.location.href = 'login.html';
    });
});

function loadDashboard() {
    var userId = sessionStorage.getItem('userId');
    
    $.ajax({
        url: 'http://localhost:8080/api/dashboard/' + userId,
        type: 'GET',
        success: function(response) {
            if (response.success) {
                updateStats(response);
                loadTransactions(response.transactions || []);
                loadChart(response.categoryData || []);
            } else {
                alert(response.message || 'Error loading dashboard');
            }
        },
        error: function() {
            // Use sample data for demo
            var sampleData = {
                totalIncome: 50000,
                totalExpenses: 32500,
                balance: 17500,
                budgetPercentage: 65,
                transactions: [
                    { date: '2026-06-15', description: 'Groceries', category: 'Food', amount: 2500, type: 'expense' },
                    { date: '2026-06-14', description: 'Salary', category: 'Income', amount: 50000, type: 'income' },
                    { date: '2026-06-13', description: 'Rent', category: 'Housing', amount: 8000, type: 'expense' },
                    { date: '2026-06-12', description: 'Netflix', category: 'Entertainment', amount: 649, type: 'expense' },
                    { date: '2026-06-11', description: 'Shopping', category: 'Shopping', amount: 3500, type: 'expense' }
                ]
            };
            updateStats(sampleData);
            loadTransactions(sampleData.transactions);
        }
    });
}

function updateStats(data) {
    $('#totalIncome').text(data.totalIncome || 0);
    $('#totalExpenses').text(data.totalExpenses || 0);
    $('#balance').text(data.balance || 0);
    $('#budgetStatus').text((data.budgetPercentage || 0) + '%');
}

function loadTransactions(transactions) {
    var html = '';
    if (transactions.length === 0) {
        html = '<tr><td colspan="5" class="text-center">No transactions found</td></tr>';
    } else {
        transactions.slice(0, 5).forEach(function(t) {
            var cls = t.type === 'income' ? 'text-success' : 'text-danger';
            var badge = t.type === 'income' ? 'bg-success' : 'bg-danger';
            html += '<tr>';
            html += '<td>' + t.date + '</td>';
            html += '<td>' + t.description + '</td>';
            html += '<td>' + t.category + '</td>';
            html += '<td class="' + cls + '">₹' + t.amount + '</td>';
            html += '<td><span class="badge ' + badge + '">' + t.type + '</span></td>';
            html += '</tr>';
        });
    }
    $('#transactionBody').html(html);
}

function loadChart(data) {
    var ctx = document.getElementById('categoryChart').getContext('2d');
    
    var chartData = {
        labels: ['Food', 'Housing', 'Shopping', 'Entertainment', 'Transport'],
        values: [4500, 8000, 3500, 2000, 1500]
    };
    
    if (data && data.length > 0) {
        chartData.labels = data.map(d => d.category);
        chartData.values = data.map(d => d.amount);
    }
    
    var colors = ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40', '#C9CBCF'];
    
    new Chart(ctx, {
        type: 'pie',
        data: {
            labels: chartData.labels,
            datasets: [{
                data: chartData.values,
                backgroundColor: colors.slice(0, chartData.values.length),
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: { position: 'bottom' }
            }
        }
    });
}

function refreshData() {
    loadDashboard();
}