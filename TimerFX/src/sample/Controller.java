package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import object.Timer;

public class Controller {
    public Slider hours;
    public Slider minutes;
    public Slider seconds;
    public Text time;
    public Button stop_continue;
    public VBox sliders;

    private Timer timer;

    public void initialize(){
        hours.valueProperty().addListener(change -> {
            String text = time.getText();
            String[] arr = text.split(":");
            int val = (int) hours.getValue();
            arr[0] = val < 10 ? "0" + val : String.valueOf(val);
            time.setText(arr[0] + ":" + arr[1] + ":" + arr[2]);
        });
        minutes.valueProperty().addListener(change -> {
            String text = time.getText();
            String[] arr = text.split(":");
            int val = (int) minutes.getValue();
            arr[1] = val < 10 ? "0" + val : String.valueOf(val);
            time.setText(arr[0] + ":" + arr[1] + ":" + arr[2]);
        });
        seconds.valueProperty().addListener(change -> {
            String text = time.getText();
            String[] arr = text.split(":");
            int val = (int) seconds.getValue();
            arr[2] = val < 10 ? "0" + val : String.valueOf(val);
            time.setText(arr[0] + ":" + arr[1] + ":" + arr[2]);
        });
    }

    public void begin(ActionEvent event) {
        int hoursValue =(int) hours.getValue();
        int minutesValue =(int) minutes.getValue();
        int secondsValue = (int) seconds.getValue();

        // to second
        int res = (hoursValue * 3600) + (minutesValue * 60) + secondsValue;

        //make new thread
        timer = new Timer(res, time);
        // set X and Y for time
        int XB = 137;
        time.setLayoutX(XB);
        int YB = 59;
        time.setLayoutY(YB);
        // and make not visible sliders
        sliders.setVisible(false);
        // reset sliders
        hours.setValue(0);
        minutes.setValue(0);
        seconds.setValue(0);
    }

    public void stop_continue(ActionEvent event) {
        if(timer.isStop()) {
            stop_continue.setText("Stop");
            timer.setStop(false);
        }else {
            stop_continue.setText("Cont");
            timer.setStop(true);
        }
    }

    public void reset(ActionEvent event) {
        if(time.getText().equals("0:00:01"))
            return;
        timer.reset();
    }

    public void anew(ActionEvent event) {
        int XA = 1;
        time.setLayoutX(XA);
        int YA = 59;
        time.setLayoutY(YA);
        timer.setStop(true);
        time.setText("00:00:00");
        sliders.setVisible(true);
    }
}