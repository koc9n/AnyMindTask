package org.test.anymindtask.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.test.anymindtask.model.Payment

@Repository
interface PaymentRepository : JpaRepository<Payment, Long>