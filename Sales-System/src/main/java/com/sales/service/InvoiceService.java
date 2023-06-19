package com.sales.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sales.Repo.InvoiceRepo;
import com.sales.entity.Invoice;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepo invoiceRepo;

	public Invoice insertInvoice(Invoice invoice) {
		if (invoiceRepo.findById(invoice.getId()).isPresent() ) {
			throw new IllegalArgumentException("Invoice ID already exists");
		}
		return invoiceRepo.save(invoice);
	}

	public void deleteInvoice(Long id) {
		invoiceRepo.deleteById(id);
	}

	public Invoice getInvoiceById(Long id) {
		return invoiceRepo.getById(id);
	}

	public List<Invoice> getAllInvoices() {
		return (List<Invoice>) invoiceRepo.findAll();
	}

	public Invoice updateInvoice(Invoice newInvoice, Long id) {
		Invoice currentInvoice = invoiceRepo.getById(id);
		currentInvoice.setInvoiceNo(newInvoice.getInvoiceNo());
		currentInvoice.setAmount(newInvoice.getAmount());
		currentInvoice.setInvoiceDate(newInvoice.getInvoiceDate());
		currentInvoice.setReceiver(newInvoice.getReceiver());
		return invoiceRepo.save(currentInvoice);
	}

}