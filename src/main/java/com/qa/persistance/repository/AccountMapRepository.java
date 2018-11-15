package com.qa.persistance.repository;

import java.util.HashMap;

import javax.enterprise.inject.Alternative;

import com.qa.persistance.domain.Accounts;
import com.qa.util.JSONutil;


@Alternative
public class AccountMapRepository implements AccountRepository{
	HashMap <Long, Accounts> map=new HashMap<Long, Accounts>();

	@Override
	public Accounts findAccount(Long accountid) {
		return map.get(accountid);		
	}

	@Override
	public String findAllAccounts() {
	return JSONutil.getJSONForObject(map.values());
	}

	@Override
	public String createAccount(String newaccount) {
		Long ID = (long) 0;
		ID++;
		Accounts anAccount = JSONutil.getObjectForJSON(newaccount, Accounts.class);
		map.put(ID, anAccount);
		return "{\"message\": \"account added\"}";
	}

	@Override
	public String updateAccount(Long accountid, String updateaccount) {
		Accounts updatedAccount = JSONutil.getObjectForJSON(updateaccount, Accounts.class);
		map.put(accountid,updatedAccount);
		return"{\"message\": \"account updated\"}";
	}

	@Override
	public String deleteAccount(Long accountid) {
		Accounts accountInDB = findAccount(accountid);
		if (accountInDB != null) {
		map.remove(accountid);}
	return "{\"message\": \"account deleted\"}";
	}
}
