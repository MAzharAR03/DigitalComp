import java.util.Scanner;

public class Input {
    private static final Scanner scanner =new Scanner(System.in);

    private Input(){}

    public static String readLine(String line){
        System.out.print(line);
        return scanner.nextLine().trim();

    }

    public static int readInt(String line){
        while (true){
            System.out.print(line);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Enter number");

        }
    }

    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches("\\d+(\\.\\d{1,2})?")) {
                float value = Float.parseFloat(input);
                if (value > 0) {
                    return value;
                }
            }
            System.out.println("Amount must be float");
        }
    }

}
