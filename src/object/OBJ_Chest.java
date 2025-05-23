package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        this.name = "chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.collision = true;

    }
}
