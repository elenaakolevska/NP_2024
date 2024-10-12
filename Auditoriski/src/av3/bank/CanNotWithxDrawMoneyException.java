package av3.bank;

public class CanNotWithxDrawMoneyException extends Exception{
    public CanNotWithxDrawMoneyException(double currentAmount, double amount){
        super(String.format("Your current amount is: %.2f, and you can not withdraw this amount: %.2d",
                currentAmount, amount));
    }

}
