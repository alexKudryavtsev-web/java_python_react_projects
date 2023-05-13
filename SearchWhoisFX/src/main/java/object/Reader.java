package object;

import java.util.*;

public class Reader {
    private String text;

    public Reader(String whoisAnswer){
        text = whoisAnswer;
    }

    public String notice(){
        int st = text.indexOf("NOTICE: ");
        int en = text.indexOf("TERMS OF USE: ");

        return text.substring(st + 7, en).trim();
    }

    public List<Data> table(){
        List<Data> result = new ArrayList<>();
        String[] split = text.split("\n");
        for (String s : split) {
            String c = s.trim();
            if(c.startsWith("URL") || c.startsWith("Name Server")
                    || c.startsWith("Domain Status"))
                continue;

            if (c.startsWith(">>>")) {
                result.add(new Data("Last update", getLast()));
                break;
            }else {
                String[] a = c.split(": ");
                result.add(new Data(a[0], a[1]));
            }
        }
        return result;
    }

    public List<String> servers(){
        List<String> result = new ArrayList<>();
        for (String s : text.split("\n")) {
            if (s.trim().startsWith("Name Server: ")) {
                int i = s.indexOf(":");
                result.add(s.substring(i+2, s.length()-1));
            }
        }
        return result;
    }

    public List<Data> domaimStatus(){
        List<Data> result = new ArrayList<>();
        for (String s : text.split("\n")) {
            if (s.trim().startsWith("Domain Status: ")) {
                int i = s.indexOf(":");
                String[] t = s.substring(i+2, s.length()-1).split(" ");
                result.add(new Data(t[0], t[1]));
            }
        }
        return result;
    }

    private String getLast(){
        String[] arr = text.split("\n");
        for (String s : arr) {
            if(s.startsWith(">>>")){
                StringBuilder s1 = new StringBuilder(s.split(": ")[1]);
                s1.delete(s1.length() - 4, s1.length()-1);
                return s1.toString().trim();
            }
        }
        throw new RuntimeException();
    }

}