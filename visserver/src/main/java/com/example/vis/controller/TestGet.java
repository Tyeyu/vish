package com.example.vis.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"http://localhost:8080", "null"})
@RestController
public class TestGet {
	@RequestMapping(value="/hell",method= RequestMethod.GET)
	
    public String sayHello(@RequestParam Integer id){
        return "id:"+id;
    }
}
