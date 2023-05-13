package object;

import java.io.File;
import java.util.*;

public class Decryptor {
    private FrequencyTable obj_text;
    private FrequencyTable obj_data;
    private String original;

    public Decryptor(){
    }

    public void setFiles(List<File> list) {
        obj_data = new FrequencyTable(list);
    }

    public void setText(String text) {
        obj_text = new FrequencyTable(text);
        original = text;
    }

    public String getOriginal() {
        return original;
    }

    public String decipher(){
        String res = toNumberPres(original);

        List<String> sym = setToNumberPres(obj_text.frequencyTable().keySet());
        List<Character> rep = new ArrayList<>(obj_data.frequencyTable().keySet());

        int indx = 0;
        for (String s : sym) {
            res = res.replace(s, "" + rep.get(indx));
            indx++;
        }
        return register(res);
    }

    private String register(String lower){
        char[] str = lower.toCharArray();
        char[] orig = original.toCharArray();
        for (int i = 0; i < orig.length; i++) {
            if(Character.isUpperCase(orig[i]))
                str[i] = Character.toUpperCase(str[i]);
        }

        StringBuilder b = new StringBuilder();
        for (char c : str) {
            b.append(c);
        }
        return b.toString();
    }

    private List<String> setToNumberPres(Set<Character> set){
        List<String> res = new ArrayList<>();
        for (Character c : set) {
            char c1 = Character.toLowerCase(c);

            res.add("-"+((int) c1)+"-");
        }
        return res;
    }

    private String toNumberPres(String text) {
        char[] arr = text.toLowerCase().toCharArray();
        Map<String, String> map = new HashMap<>();
        for (char c : arr) {
            if (Character.isLetter(c))
                map.put(String.valueOf(c), "-" + (int) c + "-");
        }
        String res = text;
        for (String key : map.keySet()) {
            res = res.replace(key, map.get(key));
            res = res.replace(key.toUpperCase(), map.get(key));
        }
        return res;
    }
}