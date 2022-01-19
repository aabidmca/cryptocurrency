package com.kotak.cryptocurrency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyListingRequestDTO;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;
import com.kotak.cryptocurrency.service.CryptoCurrencyService;

/**
 * @author Aabid
 */

@RestController
@RequestMapping("/v1/cryptocurrency")
public class CryptoCurrencyController {
	
	@Autowired private CryptoCurrencyService cryptoCurrencyService;
	
	@PostMapping
	public ResponseEntity<?> create(@Validated @RequestBody CryptoCurrencyRequestDTO request) {
		return new ResponseEntity<CryptoCurrencyResponseDTO>(cryptoCurrencyService.save(request), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getInfo(@PathVariable("id") Integer id) {
		return new ResponseEntity<String>("Served", HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getHistory(@PageableDefault(
			page = 1, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
			CryptoCurrencyListingRequestDTO request) {
		return new ResponseEntity<ListingResponse<CryptoCurrencyResponseDTO>>(
				cryptoCurrencyService.findAll(pageable, request), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @Validated @RequestBody CryptoCurrencyRequestDTO request) {
		return new ResponseEntity<CryptoCurrencyResponseDTO>(cryptoCurrencyService.update(request, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		cryptoCurrencyService.delete(id);
		return new ResponseEntity<String>("Resource deleted successfully", HttpStatus.OK);
	}
}
