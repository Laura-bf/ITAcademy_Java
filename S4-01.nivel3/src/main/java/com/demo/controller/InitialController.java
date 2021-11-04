package com.demo.controller;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class InitialController {


    @GetMapping("/test")
    public String helloGradle() {
        return "Hello Gradle!";
    }
    @GetMapping("/")
    public String helloWorld() {
    	return "HELLO WORLD!";
    }
    
    @GetMapping("/{name}")
    public String hello(@PathVariable(name = "name") String name){
    	return "Hello, " + name + "!";
    }

}
