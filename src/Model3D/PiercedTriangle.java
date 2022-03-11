package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class PiercedTriangle extends Solid{

    public PiercedTriangle(){
        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);
        indexBuffer.add(3);


        vertexBuffer.add(new Vertex(0.5,0,0,new Col(1,0,0.)));//v2
        vertexBuffer.add(new Vertex(0.5,0.2,0,new Col(1.,0.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0,0,new Col(1.,1.,0)));//v4
        vertexBuffer.add(new Vertex(0.6,-0.2,0,new Col(1,0,1.)));//v5



       /* vertexBuffer.add(new Vertex(0.8,1,0,new Col(0,1.,0)));//v3
        vertexBuffer.add(new Vertex(1,0,0,new Col(1.,0,0)));//v4
        vertexBuffer.add(new Vertex(0.9,1,1,new Col(0,1.,0)));//v8*/




        getPartList().add(new Part(TopologyType.TRIANGLE,0,1));

    }

}
