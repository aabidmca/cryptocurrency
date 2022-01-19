package com.kotak.cryptocurrency.config.db.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aabid
 */

@Entity
@Table(name = "crypto_currency")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CryptoCurrency implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "price", updatable = false)
    private BigDecimal price;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    
    public static CryptoCurrency create(CryptoCurrencyRequestDTO dto) {
    	return CryptoCurrency.builder()
    			.id(dto.getId())
    			.name(dto.getName().trim())
    			.code(dto.getCode().trim())
    			.createdAt(Timestamp.valueOf(LocalDateTime.now()))
    			.updatedAt(Timestamp.valueOf(LocalDateTime.now()))
    			.price(dto.getPrice())
    			.build();
    }
}