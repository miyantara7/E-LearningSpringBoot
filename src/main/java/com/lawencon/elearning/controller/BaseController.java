package com.lawencon.elearning.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController<T> {
	T model;

	public T readValue(String content, Class<T> kelas) throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(content, kelas);
	}

}
