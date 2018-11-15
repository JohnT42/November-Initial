package com.qa.business.service;

import com.qa.persistance.domain.Accounts;

public interface AccountService {
	public Accounts findAccount(Long accountid);

	public String findAllAccounts();

	public String createAccount(String newaccount);

	public String updateAccount(Long id, String account);

	public String deleteAccount(Long id);
}