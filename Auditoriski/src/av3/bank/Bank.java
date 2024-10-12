package av3.bank;

import javax.xml.transform.stax.StAXResult;
import java.util.Arrays;

//sekogas koga pr. vo Bank cuvame niza ili lista od Accounts i za sekoj account treba
// da povikame nekoj metod ( koj nema dr svojstva ) prajme interface
// ako imat i dr svojstva togas mozno e da trebit apstraktna klasa + interface ili samo apstr klasa
public class Bank {
    private Account[] accounts;
    private int totalAccounts;
    private int maxAccounts;

    public Bank(int maxAccounts) {
        this.totalAccounts = 0;
        this.maxAccounts = maxAccounts;
        this.accounts = new Account[maxAccounts];
    }

    public void addAccount(Account account) {
        if (totalAccounts == maxAccounts) {
            accounts = Arrays.copyOf(accounts, maxAccounts * 2);
            maxAccounts *= 2;
        }
        accounts[totalAccounts++] = account;
    }

    public double totalAssets() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getCurrentAmount();
        }
        return total;
    }

    public void addInterestsToAllAccounts() {
        for(Account account : accounts){
            if(account.getAccountType().equals(AccountType.INTEREST)){
                InterestBearingAccount interestBearingAccount = (InterestBearingAccount) account;
                interestBearingAccount.addInterest();
            }
        }
    }


}
