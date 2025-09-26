
public class Main {
    public static void main(String[] args) {

        String username = Input.readLine("Enter username:");
        String name = Input.readLine("Enter name:");
        String password = Input.readLine("Set password:");

        User user = new User(username, name, password);

        while (true) {
            System.out.println("--------------- Main Menu -------------");
            System.out.println("1. View Balance");
            System.out.println("2. Top-Up Wallet");
            System.out.println("3. View Transactions");
            System.out.println("4. Exit");

            int choice = Input.readInt("Choose option: ");

            switch (choice) {
                case 1:
                    System.out.println("Your balance: RM " + user.getWallet().getBalance());
                    break;

                case 2:
                    float amount = Input.readFloat("Enter top-up amount: ");
                    String note = Input.readLine("Enter note: ");
                    if (user.getWallet().topUp(amount, note)) {
                        System.out.println("Top-up successful!");
                    } else {
                        System.out.println("Invalid amount. Top-up failed.");
                    }
                    break;

                case 3:
                    System.out.println("--- Transaction History ---");
                    if (user.getWallet().getTransactions().isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (Transaction t : user.getWallet().getTransactions()) {
                            System.out.println(t.getType() + " RM" + t.getAmount() +
                                    " (" + t.getNote() + ") at " + t.getTimestamp());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Goodbye, " + user.getName() + "!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
