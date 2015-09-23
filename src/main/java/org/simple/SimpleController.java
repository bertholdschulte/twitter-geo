package org.simple;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/")
public class SimpleController {

	@RequestMapping(value="check")
	public String healthy() {
		
		return "IMOK";

	}
}
