import java.util.Date;

public class Deposit extends Transaction {
    public Deposit(Date date, int amount) {
        super(date, amount);
    }

    boolean isDeposit(){
        return true;
    }
}

