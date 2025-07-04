package com.estufa.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estufa.email.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, Long> {
}
