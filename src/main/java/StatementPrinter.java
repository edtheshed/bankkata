import java.util.Date;
import java.util.List;

public class StatementPrinter implements Printer {
    public static final String HEADER = "date || credit || debit || balance";
    private Displayer displayer;

    public StatementPrinter(Displayer displayer) {
        this.displayer = displayer;
    }

    @Override
    public void print(List<Transaction> transactions) {
        print(transactions, new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE));
    }

    @Override
    public void print(List<Transaction> transactions, Date startDate, Date endDate) {
        displayer.display(HEADER);
        int balance = transactions.stream()
                .filter(transaction -> transaction.isDeposit())
                .mapToInt(Transaction::getAmount)
                .sum();

        balance -= transactions.stream()
                .filter(transaction -> !transaction.isDeposit())
                .mapToInt(Transaction::getAmount)
                .sum();

        for (int transactionItem = transactions.size() - 1; transactionItem >= 0; transactionItem--) {
            Transaction tx = transactions.get(transactionItem);
            if (tx.isDeposit() && tx.getDate().after(startDate) && tx.getDate().before(endDate) ) {
                displayer.display(getDepositLine(tx, balance));
                System.out.println(getDepositLine(tx, balance));
            }
            if (tx.getDate().after(startDate) && tx.getDate().before(endDate)){
                displayer.display(getWithdrawLine(tx, balance));
                System.out.println(getWithdrawLine(tx, balance));
            }

            if(tx.isDeposit()) {
                balance -= tx.getAmount();
            } else {
                balance += tx.getAmount();
            }
        }
    }

    private String getWithdrawLine(Transaction transaction, int balance) {
        return String.format("%s || || %s.00 || %s.00", transaction.getStringDate(), transaction.getAmount(), balance);
    }

    private String getDepositLine(Transaction transaction, int balance) {
        return String.format("%s || %s.00 || || %s.00", transaction.getStringDate(), transaction.getAmount(), balance);
    }
}
