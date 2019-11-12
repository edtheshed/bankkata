import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BankAccountShould {

    @Mock
    Displayer displayer;

    @Mock
    Clock clock;


    @Test
    void print_out_a_statement_of_deposits_and_withdrawals() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2012, Calendar.JANUARY, 10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2012, 00, 13);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2012, 00, 14);

        when(clock.getToday()).thenReturn(calendar1.getTime(), calendar2.getTime(), calendar3.getTime());

        TransactionRepo transactionRepo = new MemoryTransactionRepo(clock);

        Printer printer = new StatementPrinter(displayer);
        BankAccount account = new BankAccount(transactionRepo, printer);
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);

        account.printStatement();

        InOrder inOrder = Mockito.inOrder(displayer);
        inOrder.verify(displayer).display("date || credit || debit || balance");
        inOrder.verify(displayer).display("14/01/2012 || || 500.00 || 2500.00");
        inOrder.verify(displayer).display("13/01/2012 || 2000.00 || || 3000.00");
        inOrder.verify(displayer).display("10/01/2012 || 1000.00 || || 1000.00");
    }

    @Test
    void filters_statement_by_date_range() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2012, 00, 10);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2012, 00, 13);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2012, 00, 14);
        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2012, 01, 9);
        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(2012, 02, 23);
        Calendar calendar6 = Calendar.getInstance();
        calendar6.set(2012, 03, 10);

        when(clock.getToday()).thenReturn(calendar1.getTime(), calendar2.getTime(), calendar3.getTime(),
                calendar4.getTime(), calendar5.getTime(), calendar6.getTime());

        TransactionRepo transactionRepo = new MemoryTransactionRepo(clock);

        Printer printer = new StatementPrinter(displayer);
        BankAccount account = new BankAccount(transactionRepo, printer);
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(2000);
        account.withdraw(500);
        account.deposit(3000);
        account.withdraw(400);

        account.printStatementByDate(calendar2.getTime(), calendar5.getTime());

        InOrder inOrder = Mockito.inOrder(displayer);
        inOrder.verify(displayer).display("date || credit || debit || balance");
        inOrder.verify(displayer, times(0)).display("10/04/2012 || || 400.00 || 5000.00");
        inOrder.verify(displayer).display("23/03/2012 || 3000.00 || || 5400.00");
        inOrder.verify(displayer).display("09/02/2012 || || 500.00 || 2400.00");
        inOrder.verify(displayer).display("14/01/2012 || 2000.00 || || 2900.00");
        inOrder.verify(displayer).display("13/01/2012 || || 100.00 || 900.00");
        inOrder.verify(displayer, times(0)).display("10/01/2012 || 1000.00 || || 1000.00");
    }
}
