public class BankAccount {
    private TransactionRepo transactionRepo;
    private Printer printer;

    public BankAccount(TransactionRepo transactionRepo, Printer printer) {
        this.transactionRepo = transactionRepo;

        this.printer = printer;
    }

    public void deposit(int amount) {
        transactionRepo.deposit(amount);
    }

    public void withdraw(int amount) {
        transactionRepo.withdraw(amount);
    }

    public void printStatement() {
        printer.print(transactionRepo.getTransactions());
    }
}
