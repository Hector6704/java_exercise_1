import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        var scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        if (!"quit".equals(input)) {
            System.out.println("Unknown command");
        }
    }
}