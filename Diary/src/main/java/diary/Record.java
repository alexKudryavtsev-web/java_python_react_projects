package diary;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Record implements Serializable {
    private String date, html;

    public Record(Calendar date, String html) {
        this.date = new SimpleDateFormat(Diary.FORMAT).format(date.getTime());
        this.html = html;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return html;
    }

    public void setContent(String html) {
        this.html = html;
    }

    @Override
    public String toString() {
        return date + ": " + html;
    }
}
