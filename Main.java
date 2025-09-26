
public class Main {
    public static void main(String[] args) {

        AuthenticationModel autheticate = new AuthenticationModel();
        User currentuser = null;

        while (currentuser == null) {
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice2 = Input.readInt("Choose option: ");

            switch (choice2) {
                case 1:
                    String signUp_username = Input.readLine("Enter username:");
                    String signUp_name = Input.readLine("Enter name:");
                    String signUp_password = Input.readLine("Set password:");

                    User newuser = new User(signUp_username, signUp_name, signUp_password);
                    if (!autheticate.verifyUsername(signUp_username)) {
                        System.out.println("USername taken try another");
                    } else {
                        boolean registered = autheticate.register(newuser);
                        if (registered
                        ) {
                            System.out.println("Rigistration successful can login in now");
                        } else {
                            System.out.println("Registration failed");
                        }
                    }
                    break;
                case 2:
                    String login_username = Input.readLine("Enter username: ");
                    String login_password = Input.readLine("Enter password:");

                    TransactionModel transact = new TransactionModel();

                    if (autheticate.login(login_username , login_password)) {
                        System.out.println("--------------- Main Menu -------------");
                        System.out.println("1. View Balance");
                        System.out.println("2. Top-Up Wallet");
                        System.out.println("3. View Transactions");
                        System.out.println("4. Exit");

                        int choice = Input.readInt("Choose option: ");

                        switch (choice) {
                            case 1:
                                float balance = transact.calculateBalance(login_username);
                                break;
                            /// testing git hub changes
                            case 2:
                                //float amount = Input.readFloat("Enter top-up amount: ");
                                // String note = Input.readLine("Enter note: ");
                                break;

                            case 3:
                                //System.out.println("--- Transaction History ---");
                                break;

                            case 4:
                                //System.out.println("Goodbye, " + user.getName() + "!");
                                return;

                            default:
                                System.out.println("Invalid option. Try again.");
                        }
                    } else {
                        System.out.println("Credentaials dont exist");
                    }
            }
        }
    }
}