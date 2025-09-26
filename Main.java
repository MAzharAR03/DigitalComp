
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
                    break;

                case 2:
                    float amount = Input.readFloat("Enter top-up amount: ");
                    String note = Input.readLine("Enter note: ");
                    break;

                case 3:
                    System.out.println("--- Transaction History ---");
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
