package com.kotak.cryptocurrency.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;
import com.kotak.cryptocurrency.config.db.entity.CryptoCurrencyHistory;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyHistoryRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyHistoryResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;
import com.kotak.cryptocurrency.repository.CryptoCurrencyHistoryRespository;
import com.kotak.cryptocurrency.service.CryptoCurrencyHistoryService;
import com.kotak.cryptocurrency.util.CommonUtil;
import com.kotak.cryptocurrency.util.ListingResponseUtil;

/**
 * @author Aabid
 *
 */

@Service
public class CryptoCurrencyHistoryServiceImpl implements CryptoCurrencyHistoryService{
	
	@Autowired private CryptoCurrencyHistoryRespository cryptoCurrencyHistoryRespository; 

	@Override
	@Transactional
	public CryptoCurrencyHistoryResponseDTO save(CryptoCurrencyHistoryRequestDTO request, CryptoCurrency cryptoCurrencyEntity) {
		CryptoCurrencyHistory cryptoEntity = cryptoCurrencyHistoryRespository.save(CryptoCurrencyHistory.create(request, cryptoCurrencyEntity));
		return CryptoCurrencyHistoryResponseDTO.create(cryptoEntity);
	}

	@Override
	public ListingResponse<CryptoCurrencyHistoryResponseDTO> findAll(Pageable pageable, Integer historyInDays, Integer id) {
		pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
		Page<CryptoCurrencyHistory> dbResponse = cryptoCurrencyHistoryRespository.findAll(getFilterSpecification(historyInDays, id), pageable);
		List<CryptoCurrencyHistoryResponseDTO> response = Collections.emptyList();
		if(dbResponse != null && ! dbResponse.isEmpty()) {
			response = dbResponse.stream().map(CryptoCurrencyHistoryResponseDTO::create).collect(Collectors.toList());
			return ListingResponseUtil.createResponse(response, dbResponse.getTotalElements(), dbResponse.getTotalPages());
		}
		return ListingResponseUtil.createResponse(response, 0, 0);
	}

	private Specification<CryptoCurrencyHistory> getFilterSpecification(Integer days, Integer cryptoCurrencyId) {
		System.out.println(CommonUtil.getBackTimestamp(new Date(), days));
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = 
			Arrays.asList(
					criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), 
							CommonUtil.getBackTimestamp(new Date(), days))),
					criteriaBuilder.and(criteriaBuilder.equal(root.get("cryptoCurrencyId"), cryptoCurrencyId))
					);
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}
	
}
