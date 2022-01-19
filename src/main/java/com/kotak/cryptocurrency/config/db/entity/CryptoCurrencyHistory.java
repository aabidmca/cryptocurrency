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

import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyHistoryRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aabid
 */

@Entity
@Table(name = "crypto_currency_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CryptoCurrencyHistory implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;
    
    @Column(name = "price", updatable = false)
    private BigDecimal price;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name="crypto_currency_id")
    private Integer cryptoCurrencyId;
    
    public static CryptoCurrencyHistory create(CryptoCurrencyHistoryRequestDTO dto, CryptoCurrency cryptoCurrencyEntity) {
    	return CryptoCurrencyHistory.builder()
    			.id(dto.getId())
    			.createdAt(Timestamp.valueOf(LocalDateTime.now()))
    			.price(dto.getPrice())
    			.cryptoCurrencyId(cryptoCurrencyEntity.getId())
    			.build();
    }
}