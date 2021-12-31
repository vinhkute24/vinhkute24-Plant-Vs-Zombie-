package game;
import game.sounds.Sound;
import javax.swing.*;
import java.awt.*;


public class IceShoot  extends Shot {

    public IceShoot (int locX, int locY) {

        super(locX, locY);
        ImageIcon ii = new ImageIcon("p31.png");
        image = ii.getImage();
        makeRectangle();

    }
    /**
     *
     * @return
     */
    @Override
    public Image getShotImage () {
        return image;
    }

    private void makeRectangle () {

        height = image.getHeight(null);
        width = image.getWidth(null);
        rectangle = new Rectangle(locX, locY, width, height);

    }

    public void move() {
        locX += speed;
        makeRectangle();
    }

}
