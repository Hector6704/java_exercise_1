import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;

interface Command {
    String name();
    Boolean run(Scanner scanner) throws IOException;
}
