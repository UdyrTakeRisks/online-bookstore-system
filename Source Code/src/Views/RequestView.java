package Views;

import java.util.Scanner;

public class RequestView {
    private final Scanner scanner;


    public RequestView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int displayRequestMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("            Request Action Menu          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Submit Request");
        System.out.println("2. Accept Request");
        System.out.println("3. Reject Request");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }


}
