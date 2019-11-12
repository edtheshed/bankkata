import java.util.Date;

public class Deposit extends Transaction {
    public Deposit(Date date, int amount) {
        super(date, amount);
    }

    public boolean isDeposit(){
        return true;
    }
}

