package com.kotak.cryptocurrency.model.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrencyHistory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CryptoCurrencyListingResponseDTO {

	private final Integer id;
    
	private final String name;
	
	private final String code;
	
    private final String price;
    
    private final LocalDateTime createdAt;
    
    private final List<CryptoCurrencyHistoryResponseDTO> history;
    
    public static CryptoCurrencyListingResponseDTO create(CryptoCurrencyHistory cryptoHistoryEntity) {
    	return CryptoCurrencyListingResponseDTO.builder()
    			.id(cryptoHistoryEntity.getId())
    			.createdAt(cryptoHistoryEntity.getCreatedAt().toLocalDateTime())
    			.price("$" + cryptoHistoryEntity.getPrice())
    			.build();
    }
	
}
