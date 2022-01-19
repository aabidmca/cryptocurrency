package com.kotak.cryptocurrency.model.dto.response;

import java.time.LocalDateTime;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrencyHistory;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CryptoCurrencyHistoryResponseDTO {

	private final Integer id;
    
    private final String price;
    
    private final LocalDateTime createdAt;
    
    public static CryptoCurrencyHistoryResponseDTO create(CryptoCurrencyHistory cryptoEntity) {
    	return CryptoCurrencyHistoryResponseDTO.builder()
    			.id(cryptoEntity.getId())
    			.createdAt(cryptoEntity.getCreatedAt().toLocalDateTime())
    			.price("$" + cryptoEntity.getPrice())
    			.build();
    }
	
}
