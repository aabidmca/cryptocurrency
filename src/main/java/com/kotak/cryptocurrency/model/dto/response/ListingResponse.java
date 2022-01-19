package com.kotak.cryptocurrency.model.dto.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ListingResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private HashMap<String, Object> pageInfo = new HashMap<>();
	
    List<T> data;

}