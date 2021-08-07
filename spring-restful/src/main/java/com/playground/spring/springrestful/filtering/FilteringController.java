package com.playground.spring.springrestful.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path = "/somebean")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("field1"
//						, "field2"
						);
		FilterProvider filters = new SimpleFilterProvider()
				.addFilter("SomeBeanFilter", filter);
//		filters
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
}
