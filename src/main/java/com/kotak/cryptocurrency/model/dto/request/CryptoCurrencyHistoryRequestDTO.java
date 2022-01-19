package com.kotak.cryptocurrency.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;
import com.kotak.cryptocurrency.config.db.entity.CryptoCurrencyHistory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Aabid
 */

@Data
@AllArgsConstructor
@Builder
public class CryptoCurrencyHistoryRequestDTO {

	@JsonProperty("id")
    private Integer id;
	
	@JsonProperty("cryptoCurrencyId")
    private Integer cryptoCurrencyId;
	
	@JsonProperty("price")
    private BigDecimal price;
    
	@JsonProperty("createdAt")
    private LocalDateTime createdAt;
	
	@JsonProperty("historyInDays")
	private Integer historyInDays;
    
    public static CryptoCurrencyHistoryRequestDTO buildWith(CryptoCurrency cryptoEntity) {
    	return CryptoCurrencyHistoryRequestDTO.builder()
    			.price(cryptoEntity.getPrice())
    			.build();
    }
    
    public static CryptoCurrencyHistoryRequestDTO create(CryptoCurrencyHistory cryptoHistoryEntity) {
    	return CryptoCurrencyHistoryRequestDTO.builder()
    			.id(cryptoHistoryEntity.getId())
    			.price(cryptoHistoryEntity.getPrice())
    			.cryptoCurrencyId(cryptoHistoryEntity.getId())
    			.createdAt(cryptoHistoryEntity.getCreatedAt() == null ? null : cryptoHistoryEntity.getCreatedAt().toLocalDateTime())
    			.build();
    }
}
