package com.playground.spring.springrestful.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	// URI versioning
	// Twitter uses
	@GetMapping(path = "/v1/person")
	public PersonV1 getPersonV1() {
		return new PersonV1("Peter Bosshard");
	}

	@GetMapping(path = "/v2/person")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Peter", "Bosshard"));
	}
	
	// Request parameter version
	// Good one, using the param to distinguish the service
	// I haven't thought about it before
	// Amazon uses
	@GetMapping(path = "/person/param", params = "version=1")
	public PersonV1 getPersonParamV1() {
		return new PersonV1("Peter Bosshard");
	}
	
	@GetMapping(path = "/person/param", params = "version=2")
	public PersonV2 getPersonParamV2() {
		return new PersonV2(new Name("Peter", "Bosshard"));
	}

	// Using custom header version
	// Microsoft uses
	@GetMapping(path = "/person/header", headers = "X-API-VERION=1")
	public PersonV1 getPersonHeaderV1() {
		return new PersonV1("Peter Bosshard");
	}
	
	// Enable this API make the OpenAPI broken
//	@GetMapping(path = "/person/header", headers = "X-API-VERION=2")
//	public PersonV2 getPersonHeaderV2() {
//		return new PersonV2(new Name("Peter", "Bosshard"));
//	}
	
	// Using MIME type versioning - using Accept header
	// GitHub uses
	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonProducesV1() {
		return new PersonV1("Peter Bosshard");
	}
	
	@GetMapping(path = "/person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonProducesV2() {
		return new PersonV2(new Name("Peter", "Bosshard"));
	}
	
	
}
