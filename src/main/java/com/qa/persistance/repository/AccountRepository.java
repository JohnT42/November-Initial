package com.qa.persistance.repository;


import com.qa.persistance.domain.Accounts;

public interface AccountRepository {
	public Accounts findAccount(Long accountid);

	public String findAllAccounts();

	public String createAccount(String newaccount);

	public String updateAccount(Long accountid, String updateaccount);

	public String deleteAccount(Long accountid);
}
