import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
                content = content.replaceAll("\\r\\n|\\r|\\n", " ");
                Map<String, String> map = new HashMap<>();
                String[] words = content.replaceAll("[^\\w\\s]", "").split(" ");
                for (int i = 0; i < words.length - 1; i++) {
                    String word = words[i];
                    String nextWord = words[i + 1];
                    if (map.containsKey(word)) {
                        String mostFrequentFollowingWord = map.get(word);
                        if (countOccurrences(content, word, nextWord) > countOccurrences(content, word, mostFrequentFollowingWord)) {
                            map.put(word, nextWord);
                        }
                    } else {
                        if (!word.isEmpty()) {
                            map.put(word, nextWord);
                        }
                    }
                }
                System.out.println("Entrez un mot : ");
                String word = scanner.nextLine();
                word = word.toLowerCase();
                String result = word;
                for (int i = 0; i < 19; i++) {
                    if (map.containsKey(word)) {
                        result += " " + map.get(word);
                        word = map.get(word);
                    } else {
                        break;
                    }
                }
                System.out.println(result);
                return false;
            } else {
                System.out.println("Empty file");
            }
        }
        return false;
    }

    private int countOccurrences(String content, String word, String nextWord) {
        int count = 0;
        int index = 0;
        while (index != -1) {
            index = content.indexOf(word + " " + nextWord, index);
            if (index != -1) {
                count++;
                index += word.length() + nextWord.length() + 1;
            }
        }
        return count;
    }
}