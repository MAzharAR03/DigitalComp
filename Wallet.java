import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private float balance = 0.0f;
    private final List<Transaction> transaction_history = new ArrayList<>();

    public Wallet(){}

    public float getBalance() {
        return balance;
    }

    public boolean topUp(float amount, String note) {
        if (amount <= 0.0f)
            return false;
        balance += amount;
        transaction_history.add(new Transaction("TOPUP", amount, note));
        return true;
    }

    public List<Transaction> getTransactions() {
        return transaction_history;
    }



}
