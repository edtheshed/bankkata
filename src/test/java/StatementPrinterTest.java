import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StatementPrinterTest {

    public static final String HEADER = "date || credit || debit || balance";
    @Mock
    Displayer displayer;

    @Test
    void can_print_an_empty_statement() {
        StatementPrinter printer = new StatementPrinter(displayer);

        printer.print(Collections.EMPTY_LIST);

        verify(displayer).display(HEADER);
    }
}
