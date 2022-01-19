package com.kotak.cryptocurrency.model.dto.request;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aabid
 */

@Data
@NoArgsConstructor
public class CryptoCurrencyListingRequestDTO {

	@JsonProperty("id")
	private Integer id;
    
	@JsonProperty("name")
    private String name;
    
	@JsonProperty("code")
    private String code;
    
	@JsonProperty("price")
    private BigDecimal price;
    
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date createdAtFrom;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date createdAtTo;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date updatedAt;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date updatedAtFrom;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date updatedAtTo;
    
}
