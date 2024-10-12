package av3.bank;

public abstract class Account {
    private String accountOwner;
    private int id;
    // kako so ke kreirame objekti idSeed ke se zgolemuva
    private static int idSeed = 1;
    private double currentAmount;
    private AccountType accountType;

    public Account(String accountOwner, double currentAmount) {
        this.accountOwner = accountOwner;
        this.currentAmount = currentAmount;
        this.id = idSeed;
        idSeed++;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }
    public void addAmount (double amount){
        this.currentAmount+=amount;
    }

    public void withdrawAmount (double amount) throws CanNotWithxDrawMoneyException {
        if (currentAmount < amount)
            throw new CanNotWithxDrawMoneyException(currentAmount, amount);
        this.currentAmount-=amount;
    }

    public abstract AccountType getAccountType();

    @Override
    public String toString() {
        return String.format("%d: %.2f", id, currentAmount);
    }
}
