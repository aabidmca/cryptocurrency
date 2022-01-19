package com.kotak.cryptocurrency.model.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Aabid
 */

@Data
@AllArgsConstructor
@Builder
public class CryptoCurrencyRequestDTO {

	
	@JsonProperty("id")
	private Integer id;
    
	@JsonProperty("name")
	@NotBlank(message = "CryptoCurrency name is mandatory")
    private String name;
    
	@JsonProperty("code")
	@NotBlank(message = "CryptoCurrency code is mandatory")
    private String code;
    
	@JsonProperty("price")
	@NotNull(message = "Price is mandatory")
    private BigDecimal price;
    
	@JsonProperty("createdAt")
    private LocalDateTime createdAt;
    
	@JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
    
    public CryptoCurrencyRequestDTO buildWith(CryptoCurrency cryptoEntity) {
    	return CryptoCurrencyRequestDTO.builder()
    			.id(cryptoEntity.getId())
    			.name(cryptoEntity.getName())
    			.code(cryptoEntity.getCode())
    			.createdAt(cryptoEntity.getCreatedAt() == null ? null : cryptoEntity.getCreatedAt().toLocalDateTime())
    			.updatedAt(cryptoEntity.getCreatedAt() == null ? null : cryptoEntity.getUpdatedAt().toLocalDateTime())
    			.build();
    }
    
}
