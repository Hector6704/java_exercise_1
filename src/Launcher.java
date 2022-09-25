import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

public class Launcher  {
    public static void main(String[] args) throws IOException {
        System.out.println("Bonjour");
        var scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Command> commands = new ArrayList<>();
        commands.add(new Freq());
        commands.add(new Fibo());
        commands.add(new Quit());
        commands.add(new Predict());
        while (!"quit".equalsIgnoreCase(input)) {
            boolean found = false;
            for (Command command : commands) {
                if (command.name().equalsIgnoreCase(input)) {
                    found = true;
                    command.run(scanner);
                    break;
                }
            }
            if (!found) {
                System.out.println("Unknown command");
            }
            input = scanner.nextLine();
        }
    }
    public static int fibo(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibo(n - 1) + fibo(n - 2);
    }
    public static void freq(String path) throws IOException {
        var path1 = Paths.get(path);
        if (!path1.toFile().exists()) {
            System.out.println("File not found");
        } else {
            var content = Files.readString(path1);
            if (!content.isBlank()) {
                content = content.toLowerCase();
                Stream<String> stream = Arrays.stream(content.replaceAll("[^a-zA-Z ]", "").split(" ")).filter(word -> !word.isEmpty());
                Map<String,Long> result = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3).forEach(entry -> System.out.print(entry.getKey() + " "));
            } else {
                System.out.println("Empty file");
            }
        }
    }
}
