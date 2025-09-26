public class User {
    private String username;
    private String name;
    private String password;

    public User(String username, String name, String password){
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public User(){
        username = "";
        name = "";
        password = "";
    }

    // getters
    public String getUsername(){
        return username;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
     //commit but no push
    //azhar changing user
}
