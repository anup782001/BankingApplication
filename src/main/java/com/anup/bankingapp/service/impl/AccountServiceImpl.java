package com.anup.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.anup.bankingapp.dto.AccountDto;
import com.anup.bankingapp.entity.Account;
import com.anup.bankingapp.mapper.AccountMapper;
import com.anup.bankingapp.repository.AccountRepository;
import com.anup.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl  implements AccountService {

	private AccountRepository accountRepository;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}
	@Override
	public AccountDto getAccountById(Long id) {
		
	Account account =	accountRepository.findById(id)
			.orElseThrow(()-> new RuntimeException("Account does not exist"));
		return AccountMapper.mapToAccountDto(account);
	}
	
	@Override
	public AccountDto deposit(Long id, double amount) {
				
		Account account =	accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exist"));
						
		double total =account.getBalance()+ amount ;
		account.setBalance(total);
		Account savedAccount =	accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}
	
	@Override
	public AccountDto withdraw(Long id, double amount) {
		
		Account account =	accountRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exist"));
				
			if(account.getBalance() < amount) {
				throw new RuntimeException("Insufficent balance");
				
				
			}
			double total = account.getBalance() - amount ;
		 	account.setBalance(total);
		 	Account savedAccount = accountRepository.save(account);
		 	
		 	return AccountMapper.mapToAccountDto(savedAccount);
			
	}
	
	@Override
	public List<AccountDto> getAllAccounts() {
		
		List<Account> accounts = accountRepository.findAll();
		
	return	accounts.stream().map((account)->AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList()); 
		
	}

}
