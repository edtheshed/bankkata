import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Transaction {
    private final Date date;
    private final int amount;

    public String getStringDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public int getAmount() {
        return amount;
    }

    public Transaction(Date date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public abstract boolean isDeposit();

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
