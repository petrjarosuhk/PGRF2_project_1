package Model3D;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    List<Solid> solidList = new ArrayList<>();

    public Scene(List<Solid> solidList) {
        this.solidList = solidList;
    }

    public List<Solid> getSolidList() {
        return solidList;
    }
    public void addSolid(Solid s){
        solidList.add(s);
    }
}
