import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankAccountTest {

    @Mock
    Printer printer;
    @Mock
    TransactionRepo transactionRepo;
    BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount(transactionRepo, printer);
    }

    @Test
    void can_record_a_deposit() {

        bankAccount.deposit(100);

        verify(transactionRepo).deposit(100);
    }

    @Test
    void can_record_a_withdrawal() {

        bankAccount.withdraw(100);

        verify(transactionRepo).withdraw(100);
    }

    @Test
    void can_send_a_transaction_to_printer() {
        bankAccount.deposit(1000);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, 12, 25);
        Transaction transaction = new Deposit(calendar.getTime(), 1000);

        List<Transaction> txList = Arrays.asList(transaction);


        when(transactionRepo.getTransactions()).thenReturn(txList);
        bankAccount.printStatement();

        verify(printer).print(txList);
    }
}
