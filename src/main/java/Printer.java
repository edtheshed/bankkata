import java.util.Date;
import java.util.List;

public interface Printer {

    void print(List<Transaction> transactions);

    void print(List<Transaction> transactions, Date startDate, Date endDate);
}
