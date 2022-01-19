package com.kotak.cryptocurrency.service;

import org.springframework.data.domain.Pageable;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyHistoryRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyHistoryResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;

public interface CryptoCurrencyHistoryService {

	CryptoCurrencyHistoryResponseDTO save(CryptoCurrencyHistoryRequestDTO request, CryptoCurrency cryptoCurrencyEntity);

	ListingResponse<CryptoCurrencyHistoryResponseDTO> findAll(Pageable pageable, Integer historyInDays, Integer id);
	
}
