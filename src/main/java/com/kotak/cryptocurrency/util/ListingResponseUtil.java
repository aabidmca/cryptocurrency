package com.kotak.cryptocurrency.util;

import java.util.HashMap;
import java.util.List;

import com.kotak.cryptocurrency.model.dto.response.ListingResponse;

/**
 * This class is used to send listing data
 * @author aabid
 */

public class ListingResponseUtil {

	public static <T> ListingResponse<T> createResponse(List<T> data, long totalRecords, Integer totalPages) {
		ListingResponse<T> listingResponse = new ListingResponse<T>();
		listingResponse.setData(data);
		listingResponse.setPageInfo(createPageInfo(totalRecords, totalPages));
		return listingResponse;
	}
	
	private static HashMap<String, Object> createPageInfo(long totalRecords, Integer totalPages) {
		HashMap<String, Object> pageInfo = new HashMap<>();
		pageInfo.put("totalRecords", totalRecords);
		pageInfo.put("totalPages", totalPages);
		return pageInfo;
	}
}
