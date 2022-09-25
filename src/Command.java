import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Command {
    String name();
    Boolean run(Scanner scanner) throws IOException;
}
class Quit implements Command {
    public String name() {
        return "quit";
    }
    public Boolean run(Scanner scanner) {
        return true;
    }
}
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

class Predict implements Command {
    public String name() {
        return "predict";
    }
    public Boolean run(Scanner scanner) {
        System.out.println("Entrez le chemin du fichier :");
        String path = scanner.nextLine();
        var path1 = Paths.get(path);
        if (!path1.toFile().exists()) {
            System.out.println("File not found");
        } else {
            String content = null;
            try {
                content = Files.readString(path1);
            } catch (IOException e) {
                System.out.println("Unreadable file : " + e.getClass().getName() + " : " + e.getMessage());
            }
            assert content != null;
            if (!content.isBlank()) {
                content = content.toLowerCase();
                Stream<String> stream = Arrays.stream(content.split(" ")).filter(word -> !word.isEmpty());
                Map<String, Long> result = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                result.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3).forEach(entry -> System.out.print(entry.getKey() + " "));
                System.out.println("Entrez un mot :");
                String word = scanner.nextLine();
                if (!result.containsKey(word)) {
                    System.out.println("Le mot " + word + " n'est pas présent dans le fichier");
                    return false;
                }
                Map<String, Long> neighbourResult = null;
                for (int i = 0; i < 3; i++) {
                    neighbourResult = new HashMap<>();
                    for (Map.Entry<String, Long> stringLongEntry : result.entrySet()) {
                        if (stringLongEntry.getKey().startsWith(word)) {
                            neighbourResult.merge(String.valueOf(stringLongEntry), 1L, Long::sum);
                        }
                    }
                    neighbourResult.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(1).forEach(entry -> System.out.print(entry.getKey() + " "));
                }
                System.out.println("Result :" + neighbourResult);
                return false;
            } else {
                System.out.println("Empty file");
            }
        }
        return false;
    }
}