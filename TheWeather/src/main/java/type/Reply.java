package type;

import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;

public class Reply {
    private boolean have;
    private BackgroundImage backImage = null;
    private Image precipitationImage = null;

    public boolean have(){
        return have;
    }

    public BackgroundImage get(){
        return backImage;
    }

}
