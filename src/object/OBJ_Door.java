package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Door extends SuperObject {
    public OBJ_Door(){
        this.name = "door";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = true;


    }
}
