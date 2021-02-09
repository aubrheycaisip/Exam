package com.java.exam.service;

import org.springframework.stereotype.Service;

import com.java.exam.component.Owl;

@Service
public class PolymorphismService {

	private Owl owl = new Owl();
	
	public String polymorphism() {
		System.out.println("Method sing from parent class: ");
		owl.sing();
		System.out.println("Overiding sing method: ");
		owl.sing("hoot hooooot");
		return "polymorphism";
	}
}
