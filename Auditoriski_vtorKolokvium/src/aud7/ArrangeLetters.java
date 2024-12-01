package aud7;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArrangeLetters {
    public static String arrangeSentence (String sentence){
        // 1. Stream of [k0, pSk, s0]
        // 2. map -> Stream [Ok, Skp, Os]
        // 3. sorted -> Stream [Ok, Os, Skp]
        // 4. collect -> joining the words with an empty space between them
      return   Arrays.stream(sentence.split("\\s+")).map(ArrangeLetters::arrangeWord)
                .sorted().collect(Collectors.joining(" "));

    }

    public static String arrangeWord(String word) {
        return word.chars()
                .sorted()
                .mapToObj(ch -> String.valueOf((char)ch))
                .collect(Collectors.joining());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sentence = sc.nextLine();
        System.out.println(arrangeSentence(sentence));
    }
}
