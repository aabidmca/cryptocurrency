package com.kotak.cryptocurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyHistoryResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;
import com.kotak.cryptocurrency.service.CryptoCurrencyHistoryService;

/**
 * @author Aabid
 */

@RestController
@RequestMapping("/v1/cryptocurrency")
public class CryptoCurrencyHistoryController {
	
	@Autowired private CryptoCurrencyHistoryService cryptoCurrencyHistoryService;
	
	@GetMapping("/history/{cryptoCurrencyId}")
	public ResponseEntity<?> getAll(
			@PathVariable("cryptoCurrencyId") Integer id, 
			@PageableDefault(page = 1, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, 
			@RequestParam("historyInDays") Integer historyInDays) {
		
		historyInDays = historyInDays == null ? 90 : historyInDays;
		return new ResponseEntity<ListingResponse<CryptoCurrencyHistoryResponseDTO>>(
				cryptoCurrencyHistoryService.findAll(pageable, historyInDays, id), HttpStatus.OK);
	}
}
