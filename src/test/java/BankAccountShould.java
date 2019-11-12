import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BankAccountShould {

    @Mock
    Displayer displayer;
    @Mock
    TransactionRepo transactionRepo;

    @Test
    void print_out_a_statement_of_deposits_and_withdrawals() {

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
}
