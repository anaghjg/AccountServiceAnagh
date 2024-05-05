package com.dbs.account.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.account.entity.Account;
import com.dbs.account.exception.AccountNotFoundException;
import com.dbs.account.exception.InvalidAccountTypeException;
import com.dbs.account.repository.AccountRepository;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Transactional
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		
		account.setAccountBalance(account.getAccountBalance()==null?0:account.getAccountBalance());
		
		if(account.getAccountType().equals("Savings") || account.getAccountType().equals("Current"))
		{
		return accountRepository.save(account);
		}
		else if(account.getAccountType().equals("")||account.getAccountType().equals(""))
		{
			throw new InvalidAccountTypeException("Account Type cannot be blank.");
		}
		else {
			throw new InvalidAccountTypeException("Invalid Account Type : "+account.getAccountType());
		}
		
	}

	public Account findAccountByAccNum(Long accountNumber) {
		// TODO Auto-generated method stub
		
		Optional<Account> accountInfo = Optional.ofNullable(accountRepository.findByAccountNumber(accountNumber));
		
		if(accountInfo.isPresent()) {
			return accountInfo.get();
		}
		else
		{
			throw new AccountNotFoundException("Account Number "+accountNumber+" doesnot exist");
		}
	}
	
	@Transactional
	public Account updateAccount(Long accountNumber, Account account) {
		// TODO Auto-generated method stub  
		return accountRepository.save(account);
	}
	
	@Transactional
	public void deleteAccount(Long accountNumber) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(accountNumber);
	}

}
