package memento;

import element.Picture;
import javafx.scene.image.Image;

import java.util.*;

public class ListState {
    public final static ListState LIST_STATE = new ListState();

    private ListState(){ }

    private List<Image> states = new ArrayList<>();
    private Picture picture = Picture.PICTURE;
    private int indx = -1;

    public void add(){
        states.add(picture.screen());
        indx++;
    }

    public void loadLast(){
        if(indx >= 0) {
            if(indx == 1) {
                picture.loadImage(states.get(0));
            }else {
                indx--;
                picture.loadImage(states.get(indx));
            }
        }
    }

    public void loadNext(){
        if(indx < states.size()-1) {
            indx++;
            picture.loadImage(states.get(indx));
            System.out.println(states);
        }
    }
}