import java.util.List;

public class StatementPrinter implements Printer {
    public static final String HEADER = "date || credit || debit || balance";
    private Displayer displayer;

    public StatementPrinter(Displayer displayer) {
        this.displayer = displayer;
    }

    @Override
    public void print(List<Transaction> transactions) {
        displayer.display(HEADER);
    }
}
