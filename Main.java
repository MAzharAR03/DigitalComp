import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Console Ui for sign up/ login and

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
                        System.out.println("4. Transfer to another user");
                        System.out.println("5. Exit");

                        int choice = Input.readInt("Choose option: ");

                        switch (choice) {
                            case 1:
                                double balance = transact.calculateBalance(login_username);
                                System.out.printf("Current balance for %s: %.2f%n", login_username, balance);
                                break;
                            case 2:
                                double amount = Input.readDouble("Enter top up amount");
                                String note = Input.readLine("Enter note: ");
                                if(amount<0){
                                    System.out.println("only positive values");
                                }else{
                                    Transaction topUpTransaction = new Transaction(login_username, amount, note);
                                    transact.addTransaction(topUpTransaction);
                                    System.out.println("Top up success");
                                }
                                break;

                            case 3:
                                System.out.println("Transaction history");
                                List<Transaction> transactions = transact.fetchTransactions(login_username);
                                if (transactions.isEmpty()) {
                                    System.out.println("No transactions found.");
                                } else {
                                    for (Transaction t : transactions) {
                                        System.out.printf("[%s] Amount: %.2f | Note: %s%n",
                                                t.getTimestamp(),
                                                t.getAmount(),
                                                t.getNote());
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Select User to transfer to");
                                List<String> user_list = transact.fetchUserList();

                                // remove current user
                                user_list.removeIf(u -> u.equals(login_username));
                                if (user_list.isEmpty()) {
                                    System.out.println("No other users available to transfer to.");
                                    break;
                                }
                                // print in numbered list format
                                for (int i = 0; i < user_list.size(); i++) {
                                    System.out.printf("%d. %s%n", i + 1, user_list.get(i));
                                }
                                int selection = Input.readInt("Choose recipient number:");
                                if (selection < 1 || selection > user_list.size()) {
                                    System.out.println("User does not exist");
                                    break;
                                }
                                String recipient = user_list.get(selection - 1);

                                double transferAmount = Input.readDouble("Enter amount to transfer: ");
                                if (transferAmount <= 0) {
                                    System.out.println("only positive values");
                                    break;
                                }

                                double currentBalance = transact.calculateBalance(login_username);
                                if (transferAmount > currentBalance) {
                                    System.out.println("Inssuficnet balance");
                                    break;
                                }
                                String transferNote = Input.readLine("Enter transfer note: ");
                                // minus from sender
                                transact.addTransaction(new Transaction(login_username, -transferAmount, "Transfer to " + recipient + ": " + transferNote));
                                //add to recipient
                                transact.addTransaction(new Transaction(recipient, transferAmount, "Transfer from " + login_username + ": " + transferNote));

                                System.out.println("Transfer successful to " + recipient);
                                break;
                            case 5:
                                break;

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