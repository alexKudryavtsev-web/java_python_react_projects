package structure;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InvertedIndex {
    private List<String> files = new ArrayList<>();
    private String[] sought;

    public InvertedIndex(String sought, File... files){
        this.sought = sought.chars().filter(ch ->
                Character.isLetter(ch) ||
                        Character.isDigit(ch) ||
                        (ch == ' ')).
                mapToObj(num -> String.valueOf((char) num).toLowerCase()).
                collect(Collectors.joining("")).
                split(" ");

        for (File f : files) {
            StringBuilder b = new StringBuilder();
            try(FileInputStream stream = new FileInputStream(f)) {
                int c;
                while ((c = stream.read()) != -1) {
                    b.append((char) c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.files.add(b.toString().toLowerCase());
        }
    }

    public Map<String, List<Integer>> invertedIndex(){
        Map<String, List<Integer>> res = new HashMap<>();
        for (String s : sought) {
            res.put(s, matches(s));
        }
        return res;
    }

    public Map<String, Map<Integer, List<Integer>>> invertedIndexWithPos(){
        Map<String, Map<Integer, List<Integer>>> res = new HashMap<>();
        for (String s : sought) {
            res.put(s, occurrences(s));
        }
        return res;
    }

    public List<Result> result(){
        Map<String, Map<Integer, List<Integer>>> res = invertedIndexWithPos();
        List<Result> list = new ArrayList<>();
        for (String word : res.keySet()) {
            Map<Integer, List<Integer>> val = res.get(word);
            ArrayList<Integer> f = new ArrayList<>(val.keySet());
            List<List<Integer>> i = new ArrayList<>(val.values());
            list.add(new Result(word, f, i));
        }
        return list;
    }

    private List<Integer> matches(String str){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < files.size(); i++)
            if(files.get(i).contains(str)) list.add(i);
        return list;
    }

    private Map<Integer, List<Integer>> occurrences(String str){
        Map<Integer, List<Integer>> res = new HashMap<>();
        for (Integer indx : matches(str)) {
            Matcher m = Pattern.compile("(?=("+str+"))").matcher(files.get(indx));
            List<Integer> pos = new ArrayList<>();
            while (m.find())
                pos.add(m.start());
            res.put(indx, pos);
        }
        return res;
    }

    public static class Result{
        private String word;
        private List<Integer> files;
        private List<List<Integer>> index;

        public Result(String word, List<Integer> files, List<List<Integer>> index){
            this.word = word;
            this.files = files;
            this.index = index;
        }

        public String getWord() {
            return word;
        }

        public List<Integer> getFiles() {
            return files;
        }

        public List<List<Integer>> getIndex() {
            return index;
        }

        public void setFiles(List<Integer> files) {
            this.files = files;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setIndex(List<List<Integer>> index) {
            this.index = index;
        }

        @Override
        public String toString() {
            return word +
                    "; " + files +
                    "; "+index;
        }
    }
}