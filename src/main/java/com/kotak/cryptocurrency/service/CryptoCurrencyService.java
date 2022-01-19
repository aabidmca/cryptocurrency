package com.kotak.cryptocurrency.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyListingRequestDTO;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;

/**
 * @author Aabid
 */

public interface CryptoCurrencyService {
	
	CryptoCurrencyResponseDTO find(Integer id);
	
	ListingResponse<CryptoCurrencyResponseDTO>  findAll(Pageable pageable, CryptoCurrencyListingRequestDTO request);
	
	CryptoCurrencyResponseDTO save(CryptoCurrencyRequestDTO request);

	CryptoCurrencyResponseDTO update(CryptoCurrencyRequestDTO request, Integer id);
	
	void delete(Integer id);
	
}
