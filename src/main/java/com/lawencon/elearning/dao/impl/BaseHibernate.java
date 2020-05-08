package com.lawencon.elearning.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseHibernate {

	@PersistenceContext
	protected EntityManager em;

	protected static List<Map<String, Object>> bMapperHibernate(List<Object[]> listMapping, String... obj)
			throws Exception {
		if (listMapping.isEmpty() || listMapping.get(0).length < 1) {
			throw new Exception("Result is Empty");
		}
		if (listMapping.get(0).length != obj.length) {
			throw new Exception(
					"Length not same, result = " + listMapping.get(0).length + "<> mapping = " + obj.length);
		}
		List<Map<String, Object>> listMap = new ArrayList<>();
		listMapping.forEach(valObj -> {
			Map<String, Object> map = new HashMap<>();
			for (int i = 0; i < valObj.length; i++) {
				map.put(obj[i], valObj[i]);
			}
			listMap.add(map);
		});
		return listMap;
	}

}
