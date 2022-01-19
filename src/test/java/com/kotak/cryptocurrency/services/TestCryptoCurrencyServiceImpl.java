package com.kotak.cryptocurrency.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyResponseDTO;
import com.kotak.cryptocurrency.repository.CryptoCurrencyHistoryRespository;
import com.kotak.cryptocurrency.repository.CryptoCurrencyRespository;
import com.kotak.cryptocurrency.service.impl.CryptoCurrencyHistoryServiceImpl;
import com.kotak.cryptocurrency.service.impl.CryptoCurrencyServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestCryptoCurrencyServiceImpl {

	@InjectMocks
	CryptoCurrencyServiceImpl cryptoCurrencyServiceImpl;
	
	@Mock
	CryptoCurrencyRespository cryptoCurrencyRespository;
	
	@Mock
	CryptoCurrencyHistoryServiceImpl cryptoCurrencyHistoryServiceImpl;
	
	@Mock
	CryptoCurrencyHistoryRespository cryptoCurrencyHistoryRespository; 
	
	@Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void findTest() {
		when(cryptoCurrencyRespository.findById(1)).thenReturn(Optional.of(CryptoCurrency.builder().id(1)
				.name("Bitcoin").code("BTC").createdAt(Timestamp.valueOf(LocalDateTime.now())).build()));

		CryptoCurrencyResponseDTO mockResponse = cryptoCurrencyServiceImpl.find(1);
		assertEquals(1, mockResponse.getId());
		assertEquals("Bitcoin", mockResponse.getName());
		assertEquals("BTC", mockResponse.getCode());
	}
	
	@Test
	public void saveTest() {
		when(cryptoCurrencyRespository.save(any(CryptoCurrency.class))).thenReturn(CryptoCurrency.builder().id(1)
				.name("Bitcoin").code("BTC").price(new BigDecimal("230.50")).createdAt(Timestamp.valueOf(LocalDateTime.now())).build());
		CryptoCurrencyRequestDTO request = CryptoCurrencyRequestDTO.builder().name("Bitcoin").code("BTC")
				.price(new BigDecimal("230.50")).build();
		
		CryptoCurrencyResponseDTO mockResponse = cryptoCurrencyServiceImpl.save(request);
		assertEquals(1, mockResponse.getId());
		assertEquals(request.getName(), mockResponse.getName());
		assertEquals(request.getCode(), mockResponse.getCode());
	}
	
	@Test
	public void updateTest() {
		when(cryptoCurrencyRespository.save(any(CryptoCurrency.class))).thenReturn(CryptoCurrency.builder().id(1)
				.name("Bitcoin").code("BTC").price(new BigDecimal("230.50")).createdAt(Timestamp.valueOf(LocalDateTime.now())).build());

		CryptoCurrencyRequestDTO request = CryptoCurrencyRequestDTO.builder().name("Bitcoin").code("BTC")
				.price(new BigDecimal("230.50")).build();
		
		CryptoCurrencyResponseDTO mockResponse = cryptoCurrencyServiceImpl.save(request);
		assertEquals(1, mockResponse.getId());
		assertEquals(request.getName(), mockResponse.getName());
		assertEquals(request.getCode(), mockResponse.getCode());
	}
	
	@Test
	public void deleteTest() {
		Integer id = 1;
		when(cryptoCurrencyRespository.findById(1)).thenReturn(Optional.of(CryptoCurrency.builder().id(1)
				.name("Bitcoin").code("BTC").createdAt(Timestamp.valueOf(LocalDateTime.now())).build()));
		
		cryptoCurrencyServiceImpl.delete(id);
		verify(cryptoCurrencyRespository, times(1)).deleteById(eq(id));
		
	}
}
