$(document).ready(function() {
    var userId = sessionStorage.getItem('userId');
    
    if (!userId) {
        window.location.href = 'login.html';
    }
    
    loadExpenses();
    
    // Add Expense
    $('#addExpenseForm').on('submit', function(e) {
        e.preventDefault();
        
        var expense = {
            description: $('#description').val().trim(),
            amount: parseFloat($('#amount').val()),
            category: $('#category').val(),
            expenseDate: $('#expenseDate').val()
        };
        
        if (!expense.description || !expense.amount || !expense.category) {
            alert('Please fill all fields');
            return;
        }
        
        $.ajax({
            url: 'http://localhost:8080/api/expenses/add?userId=' + userId,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(expense),
            success: function(response) {
                if (response.success) {
                    alert('Expense added successfully!');
                    $('#addExpenseForm')[0].reset();
                    loadExpenses();
                } else {
                    alert(response.message || 'Error adding expense');
                }
            },
            error: function() {
                alert('Server error. Please try again.');
            }
        });
    });
});

function loadExpenses() {
    var userId = sessionStorage.getItem('userId');
    
    $.ajax({
        url: 'http://localhost:8080/api/expenses/user/' + userId,
        type: 'GET',
        success: function(response) {
            if (response.success) {
                displayExpenses(response.expenses || []);
            }
        },
        error: function() {
            // Sample data
            var sampleExpenses = [
                { id: 1, description: 'Groceries', amount: 2500, category: 'Food', expenseDate: '2026-06-15' },
                { id: 2, description: 'Rent', amount: 8000, category: 'Housing', expenseDate: '2026-06-13' },
                { id: 3, description: 'Netflix', amount: 649, category: 'Entertainment', expenseDate: '2026-06-12' }
            ];
            displayExpenses(sampleExpenses);
        }
    });
}

function displayExpenses(expenses) {
    var html = '';
    if (expenses.length === 0) {
        html = '<tr><td colspan="5" class="text-center">No expenses found</td></tr>';
    } else {
        expenses.forEach(function(e) {
            html += '<tr>';
            html += '<td>' + e.expenseDate + '</td>';
            html += '<td>' + e.description + '</td>';
            html += '<td>' + e.category + '</td>';
            html += '<td class="text-danger">₹' + e.amount + '</td>';
            html += '<td><button class="btn btn-sm btn-danger" onclick="deleteExpense(' + e.id + ')"><i class="fas fa-trash"></i></button></td>';
            html += '</tr>';
        });
    }
    $('#expenseBody').html(html);
}

function deleteExpense(id) {
    if (confirm('Are you sure you want to delete this expense?')) {
        $.ajax({
            url: 'http://localhost:8080/api/expenses/delete/' + id,
            type: 'DELETE',
            success: function(response) {
                if (response.success) {
                    alert('Expense deleted successfully!');
                    loadExpenses();
                }
            },
            error: function() {
                alert('Error deleting expense');
            }
        });
    }
}