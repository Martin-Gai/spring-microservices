package com.in28minutes.rest.webservices.restwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// different from last course, If I changed it to @Controller, it cannot function
// I guess in last course, we define a dispatcher servlet in the web.xml, but in this course, we didn't
// The guess above is not correct, even though we didn't define a dispatcher servlet in this course; we're still using it;
// All the request would be first sent to dispatcher servlet and it'll find the mapping and map the request to the corresponding method



// Here is the answer given by chatGPT
// @Controller is used to define a class as a SpringMVC Controller
	// it typically used to build web app that return "VIEWS" (HTML pages) or perform page navigation
	// the methods in @Controller returns a logical view name or ModelAndView object, which is then resolved to an HTML page or another view format (JSP)

// @RestController is a specialized version of @Controller
	// it is used to build RESTful web services
	// methods within a @RestController class typically return "data" DIRECTLY, not view names
	// the data returned is typically serialized into formats like JSON and XML
		// If check the @RestController Interface, there's actually a @ResponseBody in it!!!! That's why it won't be a view name by default
		// Therefore in this case @RestController == @Controller + @ResponseBody, we get the same result... But still do not confuse these 2 annotation

@RestController
public class HelloWorldController {
	
	// RequestMapping(method = RequestMethod.GET) is the same as @GetMapping
	// Similarly, there's @PostMapping, @PutMapping
	
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		
		// The magic is: here we actually return a HelloWorldBean,
		// but it is finally a json object, why is that?
		
		// Because @ResponseBody + JacksonHttpMessageConverters
		return new HelloWorldBean("Hello World");
	}
	
	
	// Path Parameters
	// e.g. /user/{id}/todos/{id} => /user/1/todos/2
	@GetMapping(path = "hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello, %s", name));
	}
}
