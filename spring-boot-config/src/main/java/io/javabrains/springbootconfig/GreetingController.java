package io.javabrains.springbootconfig;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class GreetingController {
	
	@Value("${my.greeting: default value}")
	public String greetingMessage;
	
	@Value("some static message")
	private String staticMessage;
	
	@Value("${my.list.values}")
	private List<String> listValues;
	
	@Autowired
	private DbSettings dbSettings;
	
	@Autowired
	private Environment env;
	
	public GreetingController() {
		
	}
	
	@GetMapping("/greeting")
	public String greeting() {
		return "my.greeting: " + greetingMessage + "db.connection: " + dbSettings.getConnection();
	}

	@GetMapping("/envdetails")
	public String envDetails() {
		return env.toString();
	}
}
