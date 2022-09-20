import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        var scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        while (!"quit".equals(input) && !"fibo".equals(input)) {
            System.out.println("Unknown command");
            input = scanner.nextLine();
        }
        if ("fibo".equals(input)) {
            System.out.println("Enter a number");
            var number = scanner.nextInt();
            System.out.println(fibonacci(number));
        }
    }
    public  static int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}