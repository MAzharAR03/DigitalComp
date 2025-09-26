public class User {
    private final String username;
    private final String name;
    private final String password;
    private final Wallet user_wallet;

    public User(String username, String name, String password){
        this.username = username;
        this.name = name;
        this.password = password;
        this.user_wallet = new Wallet();

    }

    // getters
    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public Wallet getWallet(){
        return user_wallet;
    }
    public String getPassword(){
        return password;
    }


}
