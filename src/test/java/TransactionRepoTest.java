import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionRepoTest {

    @Mock
    private Clock clock;

    @Test
    void can_record_a_deposit_with_today_and_amount() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 12, 25);
        when(clock.getToday()).thenReturn(calendar.getTime());

        TransactionRepo transactionRepo = new MemoryTransactionRepo(clock);

        transactionRepo.deposit(100);
        List<Transaction> txList = transactionRepo.getTransactions();

        Transaction transaction = new Deposit(clock.getToday(), 100);
        assertEquals(transaction, txList.get(0));
    }


    @Test
    void can_get_multiple_transaction_with_dates_and_amounts() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2019, 12, 25);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2019, 12, 26);
        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2019, 12, 30);
        Transaction transaction1 = new Deposit(calendar1.getTime(), 100);
        Transaction transaction2 = new Deposit(calendar2.getTime(), 100);
        Transaction transaction3 = new Deposit(calendar3.getTime(), 100);
        List<Transaction> testTxList = List.of(transaction1, transaction2, transaction3);

        when(clock.getToday()).thenReturn(calendar1.getTime(), calendar2.getTime(), calendar3.getTime());
        TransactionRepo transactionRepo = new MemoryTransactionRepo(clock);
        transactionRepo.deposit(100);
        transactionRepo.deposit(100);
        transactionRepo.deposit(100);
        List<Transaction> txList = transactionRepo.getTransactions();


        assertEquals(testTxList, txList);
    }
}
