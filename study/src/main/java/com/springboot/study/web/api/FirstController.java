package com.springboot.study.web.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/calculate")
	public String calculate(int num1, int num2, String operator) {
		if(operator.equals("add")) {
			return Integer.toString(num1+num2);
		} else if(operator.equals("sub")) {
			return Integer.toString(num1-num2);
		} else if(operator.equals("mul")) {
			return Integer.toString(num1*num2);
		} else if(operator.equals("div")) {
			if(num2 == 0) {
				return "0으로 나눌 수 없습니다.";
			} else {
				return Integer.toString(num1/num2);
			}
		} else {
			return "잘못된 연산자";
		}
	}
	
	@GetMapping("/chaincalculate")
	public String chainCalculate(@RequestParam("nums") List<Integer> nums, String operator) {
		int total = 0;
		int total_mul = 1;
		
		if(operator.equals("add")) {
			for(int i : nums) {
				total += i;
			}
			return Integer.toString(total); 
		} else if(operator.equals("sub")) {
			for(int i : nums) {
				total -= i;
			}
			return Integer.toString(total); 
		} else if(operator.equals("mul")) {
			for(int i : nums) {
				total_mul *= i;
			}
			return Integer.toString(total_mul); 
		} else if(operator.equals("div")) {
			return "나눗셈은 지원되지 않습니다.";
		} else {
			return "잘못된 연산자";
		}
	}
}