import java.sql.ResultSet;
import java.sql.SQLException;

class AuthenticationModel extends Model{
    AuthenticationModel() {
        super();
    }

    public boolean verifyUsername(String username) {
        try {

            connect();
            String query = "SELECT Username FROM Authentication";
            s = c.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            while (rs.next()){
                if (username.equals(rs.getString("Username")))
                    return false;
            }
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        finally {
         try{
             s.close();
             c.close();
         } catch (Exception e) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
     }
        return true;
    }

    public boolean verifyPassword(String password)  {
        try {
            connect();
            String query = "SELECT Password FROM Authentication";
            s = c.prepareStatement(query);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                if (password.equals(rs.getString("Password")))
                    return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                s.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        }
    }

    public void register (User user){
        try{
            connect();
            String query = "INSERT INTO Authentication VALUES (?,?,?)";
            s = c.prepareStatement(query);
            s.setString(1,user.getUsername());
            s.setString(2,user.getPassword());
            s.setString(3,user.getName());
            s.executeUpdate();
            c.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
         try{
             s.close();
             c.close();
         } catch (Exception e) {
             System.err.println( e.getClass().getName() + ": " + e.getMessage() );
             System.exit(0);
         }
     }
    }

    public static void main(String[] args){
        User test = new User("a","b","c");

        AuthenticationModel am = new AuthenticationModel();
        System.out.println(am.verifyPassword("password"));
        if(am.verifyUsername(test.getUsername()))
            am.register(test);
    }
}
