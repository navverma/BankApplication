package com.capgemini.service;

import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAmountException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capgemini.service.AccountService#createAccount(int, int)
	 */

	AccountRepository accountRepository;
	Account account = new Account();

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException {
		if (amount < 500) {
			throw new InsufficientInitialBalanceException();
		}

		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setAmount(amount);

		if (accountRepository.save(account)) {
			return account;
		}

		return null;

	}

	@Override
	public int depositAmount(Account account, int amount) throws InvalidAmountException {
		int balance = account.getAmount();
		if (amount <= 0) {
			throw new InvalidAmountException();
		} else {
			balance = balance + amount;
			account.setAmount(balance);
			return account.getAmount();
		}

	}
}
