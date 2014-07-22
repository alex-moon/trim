package com.github.alex_moon.trim.text;

import java.util.Arrays;
import java.util.List;

public class Controller extends Thread {
    private static List<String> stopWords = Arrays.asList("a", "able", "about", "across", "after", "ain", "all", "almost", "also", "am", "among", "an", "and",
                    "any", "are", "aren", "as", "at", "be", "because", "been", "but", "by", "can", "cannot", "could", "couldn", "dear", "did", "didn", "do",
                    "does", "doesn", "don", "either", "else", "ever", "every", "for", "from", "get", "got", "had", "has", "hasn", "have", "he", "her", "hers",
                    "him", "his", "how", "however", "i", "if", "in", "into", "is", "isn", "it", "its", "just", "least", "let", "like", "likely", "may", "me",
                    "might", "mightn", "most", "must", "mustn", "my", "neither", "no", "nor", "not", "of", "off", "often", "on", "only", "or", "other", "our",
                    "own", "rather", "said", "say", "says", "shan", "she", "should", "shouldn", "since", "so", "some", "than", "that", "the", "their", "them",
                    "then", "there", "these", "they", "this", "tis", "to", "too", "twas", "us", "wants", "was", "wasn", "we", "were", "weren", "what", "when",
                    "where", "which", "while", "who", "whom", "why", "will", "with", "won", "would", "wouldn", "yet", "you", "your");

    public static Boolean isStopWord(String term) {
        return stopWords.contains(term);
    }
}
