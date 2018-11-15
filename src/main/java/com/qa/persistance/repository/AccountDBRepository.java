package com.qa.persistance.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistance.domain.Accounts;
import com.qa.util.JSONutil;

@Transactional(SUPPORTS) // used for read only methods
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	public Accounts findAccount(Long accountid) {
		return em.find(Accounts.class, accountid);
	}

	public String findAllAccounts() {
		Query query = em.createQuery("Select a FROM Account a");
		Collection<Accounts> accounts = (Collection<Accounts>)query.getResultList();
		return JSONutil.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED) // creates a new connection
	public String createAccount(String newaccount) {
		Accounts anAccount = JSONutil.getObjectForJSON(newaccount, Accounts.class);
		em.persist(anAccount);
		return "{\"message\": \"account added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long accountid, String updateaccount) {
		Accounts updatedAccount = JSONutil.getObjectForJSON(updateaccount, Accounts.class);
		Accounts accountFromDB = findAccount(accountid);
		if (updateaccount != null || accountFromDB != null) {
			accountFromDB.setAccountNum(updatedAccount.getAccountNum());
			accountFromDB.setFirstName(updatedAccount.getFirstName());
			accountFromDB.setLastName(updatedAccount.getLastName());
			em.merge(accountFromDB);
		}
		return "{\"message\": \"account updated\"}";
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long accountid) {
		Accounts accountInDB = findAccount(accountid);
		if (accountInDB != null) {
			em.remove(accountInDB);
		}
		return "{\"message\": \"account deleted\"}";
	}
}