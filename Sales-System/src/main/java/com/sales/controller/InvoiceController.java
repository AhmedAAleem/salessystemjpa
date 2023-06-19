package com.sales.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.entity.Invoice;
import com.sales.service.InvoiceService;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
	
	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/save")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
		Invoice newInvoice = invoiceService.insertInvoice(invoice);
		return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Invoice> deleteInvoiceById(@PathVariable("id") Long id) {
		invoiceService.deleteInvoice(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getById/{id}")
	public Invoice getInvoiceById(@PathVariable("id") Long id) {
		Invoice invoiceInfo = invoiceService.getInvoiceById(id);
		return invoiceInfo;
	}

	@GetMapping("/getAll")
	public List<Invoice> getAllInvoices() {
		List<Invoice> invoices = invoiceService.getAllInvoices();
		return invoices;

	} 

	@PutMapping("/update/{id}")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable Long id) {
		Invoice updateInvoice = invoiceService.updateInvoice(invoice, id);
		return new ResponseEntity<>(updateInvoice, HttpStatus.OK);
	}
}