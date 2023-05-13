package diary;

import javafx.collections.ObservableList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static javafx.collections.FXCollections.*;

public class Diary {
    public final static String FORMAT = "EEE MMM dd yyyy";

    private List<Record> diary =
            observableArrayList();

    public Diary() {
        File f = new File("data.text");
        if (f.length() != 0)
            try (ObjectInputStream stream =
                         new ObjectInputStream(
                                 new FileInputStream("data.text"))) {
                List<Record> list = (List<Record>) stream.readObject();
                diary.addAll(list);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public ObservableList<Record> getDiary() {
        return (ObservableList<Record>) diary;
    }

    public Record search(Calendar cal){
        String d = new SimpleDateFormat(FORMAT).format(cal.getTime());
        for (Record record : diary) {
            if (record.getDate().equals(d))
                return record;
        }
        Record newRecord = new Record(cal, "");
        diary.add(newRecord);
        return newRecord;
    }

    public void saveDiary() {
        List<Record> list =
                new ArrayList<>(diary);

        try (ObjectOutputStream writer =
                     new ObjectOutputStream(new FileOutputStream("data.text"))) {
            writer.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
