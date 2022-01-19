package com.kotak.cryptocurrency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrencyHistory;

/**
 * @author Aabid
 */

@Repository
public interface CryptoCurrencyHistoryRespository
		extends JpaRepository<CryptoCurrencyHistory, Integer>, JpaSpecificationExecutor<CryptoCurrencyHistory> {

}
