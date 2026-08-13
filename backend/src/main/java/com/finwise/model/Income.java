package com.finwise.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "income")
public class Income {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "source", nullable = false, length = 100)
    private String source;
    
    @Column(name = "amount", nullable = false)
    private Double amount;
    
    @Column(name = "income_date")
    @Temporal(TemporalType.DATE)
    private Date incomeDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public Date getIncomeDate() { return incomeDate; }
    public void setIncomeDate(Date incomeDate) { this.incomeDate = incomeDate; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}