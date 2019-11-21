package com.proky.booking.persistence.repository;

import com.proky.booking.persistence.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
