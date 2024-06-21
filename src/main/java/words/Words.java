package words;

import java.util.*;
import java.util.stream.Collectors;

public class Words {

    public static List<String> getUniqueWordsFromSentence(String sentence) {

        String[] wordsArray = sentence.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");

        Set<String> uniqueWordsSet = Arrays.stream(wordsArray).collect(Collectors.toSet());

        return new ArrayList<>(uniqueWordsSet);
    }
}
