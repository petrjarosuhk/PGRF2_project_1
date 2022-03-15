package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
/*Třída s implementací index a vertex bufferu pro Axes X*/
public class AxesX extends Solid{

    public AxesX(){
        indexBuffer.add(0);
        indexBuffer.add(1);


        vertexBuffer.add(new Vertex(0,0,0,new Col(1,0,0.)));//v1
        vertexBuffer.add(new Vertex(1,0,0,new Col(1,0,0.)));//v1


        getPartList().add(new Part(TopologyType.LINES,0,1));


    }

}
