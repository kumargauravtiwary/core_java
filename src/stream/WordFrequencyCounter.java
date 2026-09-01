package stream;

/*
File
 ↓
Files.lines()
 ↓
Stream<String>
 ↓
flatMap()
 ↓
individual words
 ↓
filter()
 ↓
groupingBy()
 ↓
counting()
 ↓
Map<String, Long>
*/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordFrequencyCounter {

    public static void main(String[] args) {

        Path filePath = Path.of("src\\stream\\large-text.txt");

        try (Stream<String> lines = Files.lines(filePath)) {

            Map<String, Long> wordFrequency = lines
                    // Convert lines into words
                    .flatMap(line -> Arrays.stream(
                            line.toLowerCase()
                                .replaceAll("[^a-z0-9\\s]", "")
                                .split("\\s+")
                    ))

                    // Remove empty strings
                    .filter(word -> !word.isBlank())

                    // Count occurrences
                    .collect(Collectors.groupingBy(
                            word -> word,
                            Collectors.counting()
                    ));

            // Display frequencies
            wordFrequency.forEach((word, count) ->
                    System.out.println(word + " -> " + count)
            );

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
