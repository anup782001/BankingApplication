package com.anup.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anup.bankingapp.dto.AccountDto;
import com.anup.bankingapp.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private AccountService accountService ;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService ;
	}
	
	//Add Account Rest API
	
	@PostMapping("/post")
	public  ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		System.out.println("post");
		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto), HttpStatus.CREATED);
		
	}
	
	//Get account api
	
	@GetMapping("/get/{id}")
	public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id){
		AccountDto accountDto = accountService.getAccountById(id);
		return ResponseEntity.ok(accountDto);
		
	}
	
	// Deposite Rest API
	
	@PutMapping("/put/{id}/deposit")
	public ResponseEntity<AccountDto> deposite(@PathVariable Long id , 
			@RequestBody Map<String , Double> request){
		
		Double amount = request.get("amount");
		
	AccountDto accountDto =	accountService.deposit(id, request.get("amount"));
		return ResponseEntity.ok(accountDto);
		
		
	}
	
	//withdraw amount
	
	@PutMapping("/put/{id}/withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id ,@RequestBody Map<String , Double> request){
		
		double amount = request.get("amount");
		AccountDto accountDto = accountService.withdraw( id, amount);
		
		return ResponseEntity.ok(accountDto);
	}
	
	//get all account rest api
	
	@GetMapping("/allAccounts")
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		List<AccountDto> accounts = accountService.getAllAccounts();

		return ResponseEntity.ok(accounts);
	}
}
