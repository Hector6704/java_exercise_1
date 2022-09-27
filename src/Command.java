import java.util.Scanner;
import java.io.IOException;

interface Command {
    String name();
    Boolean run(Scanner scanner) throws IOException;
}
