import java.util.Scanner;

class Fibo implements Command {
    public String name() {
        return "fibo";
    }
    public Boolean run(Scanner scanner) {
        System.out.println("Entrez un nombre n :");
        int n = Integer.parseInt(scanner.nextLine());
        if (n < 0) {
            System.out.println("Le nombre doit être positif");
        } else {
            System.out.println("f("+n+") = " + Launcher.fibo(n));
        }
        return false;
    }
}