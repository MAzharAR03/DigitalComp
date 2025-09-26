import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class TransactionModel extends Model{
    TransactionModel(){
        super();
    }

    public void addTransaction(Transaction transaction){
        try{
            connect();
            String query = "INSERT INTO Transactions(Username, Amount, DateTime, Note) VALUES(?,?,?,?)";
            s = c.prepareStatement(query);
            s.setString(1,transaction.getUsername());
            s.setDouble(2,transaction.getAmount());
            s.setString(3,transaction.getTimestamp());
            s.setString(4, transaction.getNote());
            s.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{
                s.close();
                c.close();
            }catch (Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
    }

    public float calculateBalance(String username){
        float balance = 0;
        ResultSet rs = null;
        try{
            connect();
            String query = "SELECT SUM(Amount) AS Total FROM Transactions WHERE Username =?";
            s = c.prepareStatement(query);
            s.setString(1,username);
            rs = s.executeQuery();
            if(rs.next()){
                balance = rs.getFloat("Total");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                s.close();
                c.close();
                try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
            }catch (Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
        }
        return balance;
    }

    public ArrayList<Transaction> fetchTransactions(String username){
        ArrayList<Transaction> transactions = new ArrayList<>();
        ResultSet rs = null;
        try{
            connect();
            String query = "SELECT * FROM Transactions WHERE Username =?";
            s = c.prepareStatement(query);
            s.setString(1,username);
            rs = s.executeQuery();
            while(rs.next()){
                transactions.add(new Transaction(
                        rs.getString("Username"),
                        rs.getDouble("Amount"),
                        rs.getString("DateTime"),
                        rs.getString("Note")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
                s.close();
                c.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactions;
    }

    public ArrayList<String> fetchUserList()  {
        ArrayList<String> userlist = new ArrayList<>();
        try {
            connect();
            String query = "Select username from Authentication";
            s = c.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            while(rs.next()){
                userlist.add(rs.getString("Username"));
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        } finally {
        try{
            s.close();
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }return userlist;

    }

    public static void main(String[] args){
        TransactionModel tm = new TransactionModel();
        tm.addTransaction(new Transaction("jon",-100.0,"Broke"));
        tm.addTransaction(new Transaction("azhar",100.0,"Broke"));
        tm.addTransaction(new Transaction("jon",50.0,"gift"));
        System.out.println(tm.calculateBalance("jon"));
    }
}
