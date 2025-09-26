import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final String filename = "user.txt";

    public static boolean saveUser(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
        writer.write(user.getUsername() +","+ user.getName() +","+ user.getPassword()+"," +  user.getWallet().getBalance());
        writer.newLine();
        writer.close();
        return true;
    }

    public static List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String username = parts[0];String name = parts[1];String password = parts[2];float balance = Float.parseFloat(parts[3]);

                User user = new User(username, name, password);
                if (balance > 0) {
                    user.getWallet().topUp(balance, "Loaded from file");
                }
                users.add(user);
            }
        }
        reader.close();
        return users;
    }

}
