package object;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject {


    public OBJ_Key(){
        this.name = "key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
