import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatementPrinterTest {

    public static final String HEADER = "date || credit || debit || balance";
    @Mock
    Displayer displayer;
    private StatementPrinter printer;

    @BeforeEach
    void setUp() {
        printer = new StatementPrinter(displayer);
    }

    @Test
    void can_print_an_empty_statement() {
        printer.print(Collections.EMPTY_LIST);

        verify(displayer).display(HEADER);
    }

    @Test
    void can_print_a_statement_with_multiple_transactions_in_reverse_chronological_order() {
        List<Transaction> txList = getMultipleTransactionList();

        printer.print(txList);

        InOrder inOrder = Mockito.inOrder(displayer);
        inOrder.verify(displayer).display(HEADER);
        inOrder.verify(displayer).display("30/12/2019 || 1000.00 || || 1800.00");
        inOrder.verify(displayer).display("26/12/2019 || || 200.00 || 800.00");
        inOrder.verify(displayer).display("25/12/2019 || 1000.00 || || 1000.00");
    }

    private static List<Transaction> getMultipleTransactionList(){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2019, 11, 25);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 11, 26);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2019, 11, 30);

        Transaction transaction1 = new Deposit(calendar1.getTime(), 1000);
        Transaction transaction2 = new Withdrawal(calendar2.getTime(), 200);
        Transaction transaction3 = new Deposit(calendar3.getTime(), 1000);

        return List.of(transaction1, transaction2, transaction3);
    }
}
