package object;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Timer implements Runnable {
    private int seconds, point = 0;
    private Text record;
    private volatile boolean isStop = false;

    public Timer(int seconds, Text record) {
        if (seconds == 0)
            throw new IllegalArgumentException();

        this.record = record;
        this.seconds = seconds;
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (point < seconds) {
            if (!isStop) {
                point++;

                record.setText(fromSecondToNormal(seconds - point + 1));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        String path = new File("src/sound.mp3").toURI().toString();
        MediaPlayer player = new MediaPlayer(new Media(path));
        player.play();
        record.setText("DONE");
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);
                record.setText("----");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            record.setText("DONE");
        }
        player.stop();
    }

    public void reset() {
        if(point != seconds)
            point = 0;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    private String fromSecondToNormal(int seconds) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(tz);
        return df.format(new Date(seconds * 1000));
    }
}