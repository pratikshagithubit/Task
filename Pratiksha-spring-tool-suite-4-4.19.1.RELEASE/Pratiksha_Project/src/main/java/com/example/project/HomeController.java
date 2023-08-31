package com.example.project;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/test")
	public HashMap<String,String> test() {
		HashMap<String,String> ob=new HashMap<>();
		ob.put("StudentName", "Pratiksha");
		ob.put("StudentRollNo", "01");
		System.out.println("This is testing");
		return ob;
	}
}
