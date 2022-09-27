import java.util.Scanner;

class Quit implements Command {
    public String name() {
        return "quit";
    }
    public Boolean run(Scanner scanner) {
        return true;
    }
}