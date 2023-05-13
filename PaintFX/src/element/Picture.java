package element;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.canvas.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import memento.ListState;

import javax.imageio.*;
import java.awt.image.*;

import java.io.*;

public class Picture {
    public static final Picture PICTURE = new Picture();

    private Picture(){ }

    private Canvas canvas;
    private Pane pane;

    public void init(Canvas canvas, Pane pane) {
        this.canvas = canvas;
        this.pane = pane;
    }

    public void addCanvas(Node node){
        makePortable(node);
        pane.getChildren().add(node);
    }

    public void clear() {
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void loadImage(Image image) {
        clear();
        canvas.getGraphicsContext2D().drawImage(image,
                0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void loadFile(File file) throws FileNotFoundException {
        clear();
        Image image = new Image(new FileInputStream(file));
        canvas.getGraphicsContext2D().drawImage(image,
                0, 0, canvas.getWidth(), canvas.getHeight());
        ListState.LIST_STATE.add();
    }

    public void saveCanvas(File image_file) {
        try {
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(screen(), null);
            ImageIO.write(renderedImage, "png", image_file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(){
        ListState.LIST_STATE.add();
        loadImage(screen());
        cancelDraw();
    }

    public void cancelDraw(){
        canvas.setDisable(false);
        pane.getChildren().remove(1);
    }

    public Image screen(){
        WritableImage wi = new WritableImage((int)canvas.getWidth(),
                (int)canvas.getHeight());
        pane.snapshot(null, wi);
        return wi;
    }

    private double xc, yc;
    private void makePortable(Node node){
        node.setOnMousePressed(event -> {
            xc = node.getTranslateX() - event.getSceneX();
            yc = node.getTranslateY() - event.getSceneY();
        });
        node.setOnMouseDragged(event -> {
            node.setTranslateX(xc+event.getSceneX());
            node.setTranslateY(yc+event.getSceneY());
        });
    }
}