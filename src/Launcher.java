import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Launcher {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        var scanner = new Scanner(System.in);
        var input = scanner.nextLine();
        while (!"quit".equals(input) && !"fibo".equals(input) && !"freq".equals(input)) {
            System.out.println("Unknown command");
            input = scanner.nextLine();
        }
        if ("fibo".equals(input)) {
            System.out.println("Enter a number");
            var number = scanner.nextInt();
            System.out.println(fibonacci(number));
        }
        if ("freq".equals(input)) {
            System.out.println("Enter a file path");
            var path = scanner.nextLine();
            var path1 = Paths.get(path);
            if (!path1.toFile().exists()) {
                System.out.println("File not found");
            } else {
                var content = Files.readString(path1);
                System.out.println(frequency(content));
            }
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

    public static String frequency(String content) {
        if (!content.isBlank()) {
            content = content.toLowerCase();
            content = content.replaceAll("[^a-z ]", "");
            var array = content.split(" ");
            var stream = Arrays.stream(array);
            var collect = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            var list = new ArrayList<>(collect.entrySet());
            list.sort((o1, o2) -> (int) (o2.getValue() - o1.getValue()));
            return list.stream().limit(3).map(Map.Entry::getKey).collect(Collectors.joining(" "));
        }
        return "Empty file";
    }
}