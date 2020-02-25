package io.bala.springbootstarter.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(name = "hello_route", path = "/hello")
	public String sayHi() {
		return "Hi";
	}
}
