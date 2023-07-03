package a04.e1;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class BankAccountFactoryImpl implements BankAccountFactory {

	@Override
	public BankAccount createBasic() {
		return new BankAccount() {
			
			int balance=0;
			
			@Override
			public boolean withdraw(int amount) {
				if(amount<=balance) {
					balance-=amount;
					return true;
				}
				return false;
			}
			
			@Override
			public int getBalance() {
				return balance;
			}
			
			@Override
			public void deposit(int amount) {
				balance += amount;
				
			}
		};
	}

	@Override
	public BankAccount createWithFee(UnaryOperator<Integer> feeFunction) {
		return new BankAccount() {
			
			int balance=0;
			
			@Override
			public boolean withdraw(int amount) {
				
				if(amount<=balance) {
					balance-=(amount+feeFunction.apply(amount));
					return true;
				}
				return false;
			}
			
			@Override
			public int getBalance() {
				return balance;
			}
			
			@Override
			public void deposit(int amount) {
				balance += amount;
				
			}
		};
	}

	@Override
	public BankAccount createWithCredit(Predicate<Integer> allowedCredit, UnaryOperator<Integer> rateFunction) {
		return new BankAccount() {
				int balance=0;
							
				@Override
				public boolean withdraw(int amount) {
					
					if(amount<=balance) {
						balance-=amount;
						return true;
					}
					var credit = balance-(amount);
					if(allowedCredit.test(Math.abs(credit))){
						balance=credit-rateFunction.apply(amount);
						return true;
					}
					return false;
				}
				
				@Override
				public int getBalance() {
					return balance;
				}
				
				@Override
				public void deposit(int amount) {
					balance += amount;
					
				}
			};
	}

	@Override
	public BankAccount createWithBlock(BiPredicate<Integer, Integer> blockingPolicy) {
		return new BankAccount() {

			int balance=0;
			boolean notBlocked = true;
			
			@Override
			public boolean withdraw(int amount) {
				
				if(amount<=balance && notBlocked) {
					balance-=amount;
					return true;
				}
				if(blockingPolicy.test(amount,balance)) {
					notBlocked=false;
				}
				return false;
			}
			
			@Override
			public int getBalance() {
				return balance;
			}
			
			@Override
			public void deposit(int amount) {
				balance += amount;
				
			}
		};
	}

	@Override
	public BankAccount createWithFeeAndCredit(UnaryOperator<Integer> feeFunction, Predicate<Integer> allowedCredit,
			UnaryOperator<Integer> rateFunction) {
		return null;
	}

}
