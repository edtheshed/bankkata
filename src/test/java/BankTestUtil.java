import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class BankTestUtil {

    public static List<Transaction> getTransactionList(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 12, 25);
        Transaction transaction = new Transaction(calendar.getTime(), 1000);

        return Arrays.asList(transaction);
    }
}
