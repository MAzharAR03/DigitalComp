import java.sql.ResultSet;
 class AuthenticationModel extends Model{
    AuthenticationModel() {
        super();
    }

    public boolean verify(String username) {
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
        if(am.verify(test.getUsername()))
            am.register(test);
    }
}
