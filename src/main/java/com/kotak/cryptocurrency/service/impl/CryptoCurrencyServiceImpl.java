package com.kotak.cryptocurrency.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kotak.cryptocurrency.config.db.entity.CryptoCurrency;
import com.kotak.cryptocurrency.exception.ResourceNotFoundException;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyHistoryRequestDTO;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyListingRequestDTO;
import com.kotak.cryptocurrency.model.dto.request.CryptoCurrencyRequestDTO;
import com.kotak.cryptocurrency.model.dto.response.CryptoCurrencyResponseDTO;
import com.kotak.cryptocurrency.model.dto.response.ListingResponse;
import com.kotak.cryptocurrency.repository.CryptoCurrencyRespository;
import com.kotak.cryptocurrency.service.CryptoCurrencyHistoryService;
import com.kotak.cryptocurrency.service.CryptoCurrencyService;
import com.kotak.cryptocurrency.util.CommonUtil;
import com.kotak.cryptocurrency.util.JsonUtils;
import com.kotak.cryptocurrency.util.ListingResponseUtil;

/**
 * @author Aabid
 */

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService{

	@Autowired private CryptoCurrencyRespository cryptoCurrencyRespository;
	@Autowired private CryptoCurrencyHistoryService cryptoCurrencyHistoryService;
	
	@Override
	public CryptoCurrencyResponseDTO find(Integer id) {
		Optional<CryptoCurrency> cryptoCurrency = cryptoCurrencyRespository.findById(id);
		if(cryptoCurrency.isPresent())
			return CryptoCurrencyResponseDTO.create(cryptoCurrency.get());
		throw new ResourceNotFoundException("Requested resource does not exist");
	}

	@Override
	public ListingResponse<CryptoCurrencyResponseDTO>  findAll(Pageable pageable, CryptoCurrencyListingRequestDTO request) {
		pageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
		Page<CryptoCurrency> dbResponse = cryptoCurrencyRespository.findAll(getFilterSpecification(request), pageable);
		List<CryptoCurrencyResponseDTO> response = Collections.emptyList();
		if(dbResponse != null && ! dbResponse.isEmpty()) {
			response = dbResponse.stream().map(CryptoCurrencyResponseDTO::create).collect(Collectors.toList());
			return ListingResponseUtil.createResponse(response, dbResponse.getTotalElements(), dbResponse.getTotalPages());
		}
		return ListingResponseUtil.createResponse(response, 0, 0);
	}

	private Specification<CryptoCurrency> getFilterSpecification(CryptoCurrencyListingRequestDTO request) {
		return (root, query, criteriaBuilder) -> {
			List<Predicate> predicates = new ArrayList<>();
			Map<String, Object> filter = JsonUtils.convertToMap(request);
			filter.entrySet().removeIf(entry -> Objects.isNull(entry.getValue()));
			for (Map.Entry<String, Object> entry : filter.entrySet()) {
				if(isDateFilter(entry.getKey())) {
					addDatePredicate(request, root, criteriaBuilder, predicates, entry.getKey());
				} else {
					predicates
							.add(criteriaBuilder.and(criteriaBuilder.equal(root.get(entry.getKey()), entry.getValue())));
				}
			}
			//setSortByAndDirection(sortByColumn, sortDirection, root, query, criteriaBuilder);
			return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
		};

	}

	private void addDatePredicate(CryptoCurrencyListingRequestDTO request, Root<CryptoCurrency> root,
			CriteriaBuilder criteriaBuilder, List<Predicate> predicates, String key) {
		switch(key) {
		case "createdAt" : 
			
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), new Timestamp(request.getCreatedAt().getTime()))));
			predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), 
					CommonUtil.getTimeTillLastSecondOfTheDay(request.getCreatedAt()))));
			break;
		case "createdAtFrom" :
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), new Timestamp(request.getCreatedAtFrom().getTime()))));
			break;
		case "createdAtTo" :
			predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), 
					CommonUtil.getTimeTillLastSecondOfTheDay(request.getCreatedAtTo())
			)));
			break;
		case "updatedAt" : 
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), new Timestamp(request.getUpdatedAt().getTime()))));
			predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), 
					CommonUtil.getTimeTillLastSecondOfTheDay(request.getUpdatedAt()))));
			break;
		case "updatedAtFrom" :
			predicates.add(criteriaBuilder.and(
					criteriaBuilder.greaterThanOrEqualTo(root.get("updatedAt"), new Timestamp(request.getUpdatedAtFrom().getTime()))));
			break;
		case "updatedAtTo" :
			predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("updatedAt"), 
					CommonUtil.getTimeTillLastSecondOfTheDay(request.getUpdatedAt())
			)));
			break;
		}
	}

	private boolean isDateFilter(String key) {
		return key.equals("createdAt") 
				|| key.equals("createdAtFrom")
				|| key.equals("createdAtTo") 
				|| key.equals("updatedAt") 
				|| key.equals("updatedAtFrom")
				|| key.equals("updatedAtTo");
	}

	@Override
	@Transactional
	public CryptoCurrencyResponseDTO save(CryptoCurrencyRequestDTO request) {
		CryptoCurrency entity = cryptoCurrencyRespository.save(CryptoCurrency.create(request));
		createHistory(entity);
		return CryptoCurrencyResponseDTO.create(entity);
	}

	@Override
	@Transactional
	public CryptoCurrencyResponseDTO update(CryptoCurrencyRequestDTO request, Integer id) {
		Optional<CryptoCurrency> dbResponse = cryptoCurrencyRespository.findById(id);
		if(dbResponse.isPresent()) {	
			CryptoCurrency existing = dbResponse.get();
			CryptoCurrency updateRequset = CryptoCurrency.create(request);
			existing.setCode(updateRequset.getCode() == null ? existing.getCode() : updateRequset.getCode().trim());
			existing.setName(updateRequset.getName() == null ? existing.getName() : updateRequset.getName().trim());
			existing.setPrice(updateRequset.getPrice() == null ? existing.getPrice() : updateRequset.getPrice());
			existing.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
			cryptoCurrencyRespository.save(existing);
			createHistory(existing);
			return CryptoCurrencyResponseDTO.create(existing);
		}
		throw new ResourceNotFoundException("Resource not found for this id");
	}

	private void createHistory(CryptoCurrency entity) {
		CryptoCurrencyHistoryRequestDTO historyRequest = CryptoCurrencyHistoryRequestDTO.buildWith(entity);
		cryptoCurrencyHistoryService.save(historyRequest, entity);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		Optional<CryptoCurrency> dbResponse = cryptoCurrencyRespository.findById(id);
		CryptoCurrency entity = new CryptoCurrency();
		if(dbResponse.isPresent()) {	
			entity.setId(id);
			entity.setPrice(BigDecimal.ZERO);
			createHistory(entity);
			cryptoCurrencyRespository.deleteById(id);
			return;
		}
		throw new ResourceNotFoundException("Resource not found for this id");
	}
}
