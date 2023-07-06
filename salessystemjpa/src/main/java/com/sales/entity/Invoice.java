package com.sales.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "invoice")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Invoice {

	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(value = 1L, message = "Invoice number must be at least 1")
    @Max(value = 9999999999L, message = "Invoice number cannot exceed 9999999999")
	@Column(name = "invoice_No")
	private long invoiceNo;
	
	@NotNull
	@Size(min=2, max=30, message = "Receiver name must be between 2 and 30 characters")
	@Column(name = "receiver")
	private String receiver;
	
	@NotNull
	@DecimalMin(value = "1.00" , message = "invalid input .. Min amount is 1" )
    @DecimalMax( value = "10000.00" , message = "invalid input .. Max amount is 10000")
	@Column(name = "amount")
	private Double amount;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Past(message = "Date must be in the past")
	@Column(name = "invoice_Date")
	private Date invoiceDate;

	public long getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


}

