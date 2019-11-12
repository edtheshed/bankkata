import java.util.ArrayList;
import java.util.List;

public class MemoryTransactionRepo implements TransactionRepo {
    private Clock clock;
    private List<Transaction> transactions;

    public MemoryTransactionRepo(Clock clock) {
        transactions = new ArrayList<>();
        this.clock = clock;
    }

    @Override
    public void deposit(int amount) {
        transactions.add(new Deposit(clock.getToday(), amount));
    }

    @Override
    public void withdraw(int amount) {
        transactions.add(new Withdrawal(clock.getToday(), amount));
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }
}
