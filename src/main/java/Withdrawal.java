import java.util.Date;

public class Withdrawal extends Transaction {
    public Withdrawal(Date date, int amount) {
        super(date, amount);
    }

    public boolean isDeposit(){
        return false;
    }
}
