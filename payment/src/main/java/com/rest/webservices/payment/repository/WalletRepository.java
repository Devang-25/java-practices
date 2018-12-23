package com.rest.webservices.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.rest.webservices.payment.domain.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>, 
JpaSpecificationExecutor<Wallet> {
	
}
