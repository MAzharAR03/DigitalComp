import java.time.LocalDateTime;

public class Transaction {
    private String username;
    private final String type;
    private final float amount;
    private final String datetime;
    private final String note;

    public Transaction(String username, String type, float amount, String note) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.note = note;
        this.datetime = LocalDateTime.now().toString();
    }

    public String getType(){
        return type; }
    public float getAmount(){
        return amount; }
    public String getTimestamp() {
        return datetime; }
    public String getNote() {
        return note; }

}
