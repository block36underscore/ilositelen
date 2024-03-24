package gay.block36.ilositelen;

import java.io.File;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Sitelen {
    static HashMap<String, String> table = new HashMap<>();

    public static void readFile() {
        try {
            var url = Sitelen.class.getClassLoader().getResource("sitelenpona.txt");
            var file = new File(url.toURI());
            var scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                var entry = parseWordEntry(scanner.nextLine());
                table.put(entry.getKey(), entry.getValue());
            }
            for (String k: table.keySet()) {
                System.out.println(k +": " +table.get(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String transliterate(String latinText) {
        StringBuilder output = new StringBuilder();
        var prev = false;
        for (String word: latinText.split("((?<=\\P{L})|(?=\\P{L}))")) {
            var sp = table.get(word);
            if (sp != null) {
                output.append(sp);
                prev = true;
            } else {
                if (prev && word.equals(" ")) {
                    prev = false;
                    continue;
                }
                prev = false;
                output.append(word);
            }
        }

        return output.toString();
    }

    private static Map.Entry<String, String> parseWordEntry(String line) {
        var parts = line.split("\\t");
        var word = parts[0];
        var character = parts[1];
        return new AbstractMap.SimpleEntry<>(word, character);
    }
}
