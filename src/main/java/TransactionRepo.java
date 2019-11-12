import java.util.List;

public interface TransactionRepo {

    void deposit(int amount);

    void withdraw(int amount);

    List<Transaction> getTransactions();

}

