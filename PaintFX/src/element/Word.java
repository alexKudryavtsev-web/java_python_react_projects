package element;

import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import memento.ListState;

public class Word {
    public static final Word WORD = new Word();

    private Picture picture = Picture.PICTURE;
    private Text text = new Text();

    private Word(){
        MenuItem save = new MenuItem("Save image");
        save.setOnAction(event -> picture.draw());

        MenuItem cancel = new MenuItem("Cancel");
        cancel.setOnAction(event -> picture.cancelDraw());

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(save, cancel);
        text.setOnContextMenuRequested(event ->
                contextMenu.show(text, event.getScreenX(), event.getScreenY())
        );
    }

    public void setFont(Font font){
        text.setFont(font);
    }

    public void setText(String text){
        this.text.setText(text);
    }

    public void setColor(Color color){
        text.setFill(color);
    }

    public void setUnderline(boolean has){
        text.setUnderline(has);
    }

    public void setStrikethrough(boolean has){
        text.setStrikethrough(has);
    }

    public Font getFont(){
        return text.getFont();
    }

    public void addCanvas(){
        Picture.PICTURE.addCanvas(text);
    }
}