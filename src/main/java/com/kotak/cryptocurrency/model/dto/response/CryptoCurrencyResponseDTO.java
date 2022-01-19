package com.kotak.cryptocurrency.model.dto.response;

import java.time.LocalDateTime;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CryptoCurrencyResponseDTO {

	private final Integer id;
    
    private final String name;
    
    private final String code;
    
    private final String price;
    
    private final LocalDateTime createdAt;
    
    private final LocalDateTime updatedAt;
    
    public static CryptoCurrencyResponseDTO create(CryptoCurrency cryptoEntity) {
    	return CryptoCurrencyResponseDTO.builder()
    			.id(cryptoEntity.getId())
    			.code(cryptoEntity.getCode())
    			.name(cryptoEntity.getName())
    			.createdAt(cryptoEntity.getCreatedAt() == null ? null : cryptoEntity.getCreatedAt().toLocalDateTime())
    			.updatedAt(cryptoEntity.getUpdatedAt() == null ? null : cryptoEntity.getUpdatedAt().toLocalDateTime())
    			.price("$" + cryptoEntity.getPrice())
    			.build();
    }
}
