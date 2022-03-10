package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class AxesY extends Solid{

    public AxesY(){
        indexBuffer.add(0);
        indexBuffer.add(1);


        vertexBuffer.add(new Vertex(0,1,0,new Col(0,1,0.)));//v1
        vertexBuffer.add(new Vertex(0,0,0,new Col(0,1,0.)));//v1


        getPartList().add(new Part(TopologyType.LINES,0,1));


    }

}
