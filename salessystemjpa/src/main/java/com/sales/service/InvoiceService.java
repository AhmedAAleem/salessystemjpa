package com.sales.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.sales.Repo.InvoiceRepo;
import com.sales.entity.Invoice;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepo invoiceRepo;

	public Invoice insertInvoice(Invoice invoice) {
		return invoiceRepo.save(invoice);
	}

//	public void deleteInvoice(Long id) {
//		invoiceRepo.deleteById(id);
//		
//	}

	public boolean deleteInvoice(Long id) {

		invoiceRepo.deleteById(id);
		return false;

	}

	public Invoice getInvoiceById(Long id) {
		return invoiceRepo.getById(id);
	}

	public List<Invoice> getAllInvoices() {
		return (List<Invoice>) invoiceRepo.findAll();
	}

	public Invoice updateInvoice(Invoice newInvoice, Long id) {
		Optional<Invoice> optionalInvoice = invoiceRepo.findById(id);
		if (optionalInvoice.isEmpty()) {
			throw new IllegalArgumentException("Invoice id no " + id + " not found , Please check again the id number");
		}
		Invoice currentInvoice = invoiceRepo.getById(id);
		currentInvoice.setInvoiceNo(newInvoice.getInvoiceNo());
		currentInvoice.setAmount(newInvoice.getAmount());
		currentInvoice.setInvoiceDate(newInvoice.getInvoiceDate());
		currentInvoice.setReceiver(newInvoice.getReceiver());
		return invoiceRepo.save(currentInvoice);
	}

}