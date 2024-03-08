package org.example;

public interface AccountService {
	public void transferMoney(long sourceAccountId, long targetAccountId,
			double amount);
}
