package com.java.exam.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.exam.model.BankAccount;
import com.java.exam.request.DepositRequest;
import com.java.exam.request.UserRequest;
import com.java.exam.response.JwtResponse;
import com.java.exam.security.AppUserDetails;
import com.java.exam.security.CurrentUser;
import com.java.exam.service.MiniBankService;
import com.java.exam.util.JwtUtil;

@RestController
@RequestMapping("/mini-bank")
public class MiniBankController {
	
	@Autowired
	private MiniBankService miniBankService;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/create-account")
	public ResponseEntity<?> createAccount(@RequestBody UserRequest request){
		return new ResponseEntity<Map<String, Object>>(miniBankService.createAccount(request), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequest request){
		Authentication authenication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), 
						request.getPassword()
						)
				);
		AppUserDetails appUserDetails = (AppUserDetails) authenication.getPrincipal();
		String token =  jwtUtil.generateToken(appUserDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/deposit")
	public ResponseEntity<?> deposit(@CurrentUser AppUserDetails appUserDetails, @RequestBody DepositRequest request){
		return new ResponseEntity<Map<String, Object>>(miniBankService.deposit(appUserDetails, request), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/withdrew")
	public ResponseEntity<?> withdrew(@CurrentUser AppUserDetails appUserDetails, @RequestBody DepositRequest request){
		return new ResponseEntity<Map<String, Object>>(miniBankService.withdrew(appUserDetails, request), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@PostMapping("/transfer")
	public ResponseEntity<?> transfer(@CurrentUser AppUserDetails appUserDetails, @RequestBody DepositRequest request){
		return new ResponseEntity<Map<String, Object>>(miniBankService.transfer(appUserDetails, request), HttpStatus.OK);
	}
	
	@Secured("ROLE_USER")
	@GetMapping("/view-bank-account")
	public ResponseEntity<?> view(@CurrentUser AppUserDetails appUserDetails, @RequestParam String accountNumber){
		return new ResponseEntity<BankAccount>(miniBankService.viewBankAccount(appUserDetails, accountNumber), HttpStatus.OK);
	}
	
	
}
