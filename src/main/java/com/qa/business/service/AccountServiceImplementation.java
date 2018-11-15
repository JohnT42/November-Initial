package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistance.domain.Accounts;
import com.qa.persistance.repository.AccountRepository;
import com.qa.util.JSONutil;

public class AccountServiceImplementation implements AccountService{
	@Inject
	private AccountRepository repo;
	
	
	@Override
	public String findAllAccounts() {
		return repo.findAllAccounts();
	}

	@Override
	public String createAccount(String jsonString) {
		Accounts newaccount=JSONutil.getObjectForJSON(jsonString, Accounts.class);
		if(newaccount.getAccountNum().equals("9999")) {return "{\"message\": \"account is blocked\"}";
		}else{return "{\"message\": \"account added\"}";
		}
	}

	@Override
	public String updateAccount(Long accountid, String jsonString) {
		return repo.updateAccount(accountid, JSONutil.getObjectForJSON2(jsonString, Accounts.class));
	}

	@Override
	public String deleteAccount(Long accountid) {
		return repo.deleteAccount(accountid);
	}

	@Override
	public Accounts findAccount(Long accountid) {
		return repo.findAccount(accountid);
	}

}
