package com.kotak.cryptocurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;

/**
 * @author Aabid
 */

@Repository
public interface CryptoCurrencyRespository
		extends JpaRepository<CryptoCurrency, Integer>, JpaSpecificationExecutor<CryptoCurrency> {

}
