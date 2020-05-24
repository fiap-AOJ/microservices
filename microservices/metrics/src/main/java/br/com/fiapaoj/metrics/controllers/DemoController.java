package br.com.fiapaoj.metrics.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping
	ResponseEntity<Map<String, String>> getDemo() {
		final Map<String, String> data = getData();
		return ResponseEntity//
				.ok(data);
	}

	private Map<String, String> getData() {
		final Map<String, String> data = new HashMap<>();
		data.put("test", "test");
		data.put("oi", "nop");

		return data;
	}
}