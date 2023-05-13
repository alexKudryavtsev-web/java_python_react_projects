package element;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.io.*;

public class Photo {
    public final static Photo PHOTO = new Photo();

    private ImageView view = new ImageView();
    private Picture picture = Picture.PICTURE;

    private Photo(){
        view.setFitWidth(300);
        view.setFitHeight(300);

        MenuItem save = new MenuItem("Save image");
        save.setOnAction(event -> picture.draw());

        MenuItem cancel = new MenuItem("Cancel");
        cancel.setOnAction(event -> picture.cancelDraw());

        MenuItem size_set = new MenuItem("Size");
        size_set.setOnAction(event -> {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        getClass().
                                getResource("../samples/fit_image_size.fxml"));
                stage.setTitle("Size Setting");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException exc) {
                System.out.println(exc);
            }
        });

        MenuItem incrWidth = new MenuItem("Increment width");
        incrWidth.setOnAction(event -> PHOTO.incrementWidth());

        MenuItem incrHeight = new MenuItem("Increment height");
        incrHeight.setOnAction(event -> PHOTO.incrementHeight());

        MenuItem decrWidth = new MenuItem("Decrement width");
        decrWidth.setOnAction(event -> PHOTO.decrementWidth());

        MenuItem decrHeight = new MenuItem("Decrement height");
        decrHeight.setOnAction(event -> PHOTO.decrementHeight());

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(save, cancel, new SeparatorMenuItem(),
                size_set, new SeparatorMenuItem(),
                incrWidth, incrHeight,
                new SeparatorMenuItem(), decrWidth, decrHeight);
        view.setOnContextMenuRequested(event ->
                contextMenu.show(view, event.getScreenX(), event.getScreenY())
        );
    }

    public void loadImage(File image){
        try {
            view.setImage(new Image(new FileInputStream(image)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Picture.PICTURE.addCanvas(view);
    }

    private void decrementWidth(){
        view.setFitWidth(view.getFitWidth()-1);
    }

    private void decrementHeight(){
        view.setFitHeight(view.getFitHeight()-1);
    }

    public void setHeight(int height){
        view.setFitHeight(height);
    }

    public void setWidth(int width){
        view.setFitWidth(width);
    }

    private void incrementHeight(){
        view.setFitHeight(view.getFitHeight()+1);
    }

    private void incrementWidth(){
        view.setFitWidth(view.getFitWidth()+1);
    }
}
