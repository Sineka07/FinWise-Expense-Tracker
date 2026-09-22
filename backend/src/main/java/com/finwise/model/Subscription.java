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
@Table(name = "subscriptions")
public class Subscription {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
	@Column(name = "service_name", nullable = false, length = 100)
	private String serviceName;
    
	@Column(name = "amount", nullable = false)
	private Double amount;
    
	@Column(name = "billing_cycle", length = 20)
	private String billingCycle = "MONTHLY";
    
	@Column(name = "renewal_date")
	@Temporal(TemporalType.DATE)
	private Date renewalDate;
    
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
    
	public String getServiceName() { return serviceName; }
	public void setServiceName(String serviceName) { this.serviceName = serviceName; }
    
	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
    
	public String getBillingCycle() { return billingCycle; }
	public void setBillingCycle(String billingCycle) { this.billingCycle = billingCycle; }
    
	public Date getRenewalDate() { return renewalDate; }
	public void setRenewalDate(Date renewalDate) { this.renewalDate = renewalDate; }
    
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
}