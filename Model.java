import java.sql.*;
public class Model {
    protected Connection c;
    protected PreparedStatement s;
    protected String path;

    Model(String path) {
        this.path = path;
    }

    Model() {
        path = "jdbc:sqlite:DigitalCompDB.db";
    }

    public void connect(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection(path);
            c.setAutoCommit(false);

        }catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public static void main(String[] args){
        Model a = new Model("jdbc:sqlite:DigitalCompDB.db");
        a.connect();
    }

}