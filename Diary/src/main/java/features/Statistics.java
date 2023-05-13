package features;

import diary.Record;
import org.jsoup.Jsoup;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;

public class Statistics {
    private String text;
    private Record record;

    public Statistics(Record record) {
        this.record = record;
        text = Jsoup.parse(record.getContent()).body().text();
    }

    public int countSymbol() {
        return text.length();
    }

    public int countWord() {
        return text.split(" ").length;
    }

    public int countSpaces(){
        return countOccurrence(' ', text);
    }

    public int countDigits(){
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c))
                count++;
        }
        return count;
    }

    public int getSize() {
        try (ByteArrayOutputStream byteObject =
                     new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(byteObject)) {

            objectOutputStream.writeObject(record);
            objectOutputStream.flush();

            return byteObject.toByteArray().length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Map<Character, Float> frequencyTable(){
        Map<Character, Float> ret = new HashMap<>();
        text = clearString(text);
        List<Character> unique = uniqueChars(text);
        for (Character val : unique)
            ret.put(val, percent(val, text));

        return sort(ret);
    }

    private static String clearString(String value){
        StringBuilder res = new StringBuilder();
        for (char c : value.toLowerCase().toCharArray()) {
            if(Character.isLetter(c))
                res.append(c);
        }
        return res.toString();
    }

    private static List<Character> uniqueChars(String text){
        List<Character> uniq = new ArrayList<>();
        for (char c : text.toCharArray()) {
            if(!uniq.contains(c))
                uniq.add(c);
        }
        return uniq;
    }

    private static int countOccurrence(char ch, String text){
        Matcher m = Pattern.compile("(?=("+ch+"))").matcher(text);
        int count = 0;
        while (m.find()) count++;
        return count;
    }

    private static float percent(char ch, String text){
        int first = countOccurrence(ch, text);
        int second = text.length();

        if(first > second)
            throw new IllegalArgumentException();

        double fVal = (double) first / second;
        return (float) fVal * 100;
    }

    private static <T> Map<T, Float> sort(Map<T, Float> map) {
        Map<T, Float> result = new LinkedHashMap<>();
        Stream<Map.Entry<T, Float>> st = map.entrySet().stream();
        st.sorted(Comparator.comparing(Map.Entry::getValue))
                .forEach(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }
}
