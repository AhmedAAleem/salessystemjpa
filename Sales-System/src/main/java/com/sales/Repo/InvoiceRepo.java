package com.sales.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.entity.Invoice;

@Repository
public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

	Invoice getById (Long id);

}
