import java.io.IOException;
import java.util.Scanner;

class Freq implements Command {
    public String name() {
        return "freq";
    }
    public Boolean run(Scanner scanner) throws IOException {
        System.out.println("Entrez le chemin du fichier :");
        String path = scanner.nextLine();
        Launcher.freq(path);
        return false;
    }
}