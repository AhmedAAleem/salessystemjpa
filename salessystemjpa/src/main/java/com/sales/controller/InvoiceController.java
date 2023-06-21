package com.sales.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public ResponseEntity<?> addInvoice(@Valid @RequestBody Invoice invoice, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {

			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}

		Invoice newInvoice = invoiceService.insertInvoice(invoice);
		return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInvoiceById(@PathVariable("id") Long id) {
		try {
			invoiceService.deleteInvoice(id);
			return ResponseEntity.ok("Invoice with ID " + id + " has been deleted successfully.");
		} catch (EmptyResultDataAccessException e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body("No invoice found with the ID " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete invoice with ID " + id);
		}
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
	public ResponseEntity<?> updateInvoice(@Valid @RequestBody Invoice invoice, BindingResult bindingResult,
			@PathVariable Long id) {
		if (bindingResult.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMessage.append(error.getDefaultMessage()).append("; ");
			}
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}

		try {
			Invoice updatedInvoice = invoiceService.updateInvoice(invoice, id);
			return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return ((BodyBuilder) ResponseEntity.notFound()).body(e.getMessage());
		}
	}

}