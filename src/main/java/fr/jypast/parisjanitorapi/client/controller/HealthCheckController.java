package fr.jypast.parisjanitorapi.client.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/status")
public class HealthCheckController {
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String healthCheck() {
		return "API online";
	}
}
