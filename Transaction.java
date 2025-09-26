import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String username;
    private final double amount;
    private final String datetime;
    private final String note;

    public Transaction(String username, double amount, String note) {
        this.username = username;
        this.amount = amount;
        this.note = note;
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.datetime = LocalDateTime.now().format(myFormatObj);
    }

    public double getAmount(){
        return amount; }
    public String getTimestamp() {
        return datetime; }
    public String getNote() {
        return note; }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
