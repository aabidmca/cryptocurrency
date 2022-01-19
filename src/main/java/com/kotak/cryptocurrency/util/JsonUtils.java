package com.kotak.cryptocurrency.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aabid
 *
 */

@Slf4j
public final class JsonUtils {

	
	private static JsonUtils INSTANCE = new JsonUtils();
	private ObjectMapper mapper ;


	private JsonUtils() {
		mapper = new ObjectMapper();
	}
	
	
	/**
	 * @param object
	 * @return
	 */
	public static Map<String, Object> convertToMap(final Object object) {
		try {
			String json = INSTANCE.mapper.writeValueAsString(object);
			final TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
			};
			return INSTANCE.mapper.readValue(json, typeReference);
		} catch (final IOException | IllegalArgumentException e) {
			log.error("Unable to encode object", e);
		}
		return Collections.emptyMap();
	}
	
}
