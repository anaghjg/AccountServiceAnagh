package com.dbs.account.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.account.entity.Account;
import com.dbs.account.service.AccountService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/accounts")

public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	Map <String,Object> responseJson = new LinkedHashMap<String,Object>();
	
	@PostMapping("/createAccount")
	//public Account createAccount(@RequestBody Account account, HttpServletResponse response) {
	
	public ResponseEntity<?> createAccount(@RequestBody Account account){
		
		accountService.createAccount(account);
		responseJson.put("status", 1);
		responseJson.put("message", "Account created successfully.");
		responseJson.put("data", account);
		return new ResponseEntity<>(responseJson,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/findAccount/{accNum}")
	public ResponseEntity<?> findAccountByAccNum(@PathVariable("accNum") Long accountNumber, HttpServletResponse response) {

			Account account = accountService.findAccountByAccNum(accountNumber);
			responseJson.put("status", 1);
			responseJson.put("data", account);
			return new ResponseEntity<>(responseJson,HttpStatus.OK);
	}
	
	@PutMapping("/updateAccount/{accNum}")
	public ResponseEntity<?> updateAccount(@PathVariable("accNum") Long accountNumber, @RequestBody Account account) {
		
		Account accountDetails = accountService.findAccountByAccNum(accountNumber);
		
		accountDetails.setAccountType(account.getAccountType());
		accountDetails.setAccountBalance(account.getAccountBalance());
		
		accountService.createAccount(accountDetails);
		
		responseJson.put("status", 1);
		responseJson.put("message", "Account updated successfully.");
		responseJson.put("data", accountDetails);
		
		return new ResponseEntity<>(responseJson,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeAccount/{accNum}") 
	public ResponseEntity<?> deleteAccount(@PathVariable("accNum") Long accountNumber) {
		
		accountService.findAccountByAccNum(accountNumber);
		accountService.deleteAccount(accountNumber); 
		
		responseJson.put("status", 1);
		responseJson.put("message", "Account removed successfully.");	
		responseJson.put("data", null);
		return new ResponseEntity<>(responseJson,HttpStatus.OK);
	}

}
