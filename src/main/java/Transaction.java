import java.util.Date;
import java.util.Objects;

public class Transaction {
    private final Date date;
    private final int amount;

    public Transaction(Date date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount);
    }
}
