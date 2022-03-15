package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
/*Třída s implementací index a vertex bufferu pro Axes X*/
public class AxesZ extends Solid{

    public AxesZ(){
        indexBuffer.add(0);
        indexBuffer.add(1);


        vertexBuffer.add(new Vertex(0,0,1,new Col(0,0,1.)));//v1
        vertexBuffer.add(new Vertex(0,0,0,new Col(0,0,1.)));//v1


        getPartList().add(new Part(TopologyType.LINES,0,1));


    }

}
