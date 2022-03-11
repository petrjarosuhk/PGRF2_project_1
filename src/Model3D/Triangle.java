package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Triangle extends Solid{

    public Triangle(){


        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);
        indexBuffer.add(3);


        vertexBuffer.add(new Vertex(0.8,0,0.2,new Col(1,0,0.)));//v2
        vertexBuffer.add(new Vertex(0.8,0.2,0.2,new Col(1.,0.,0)));//v3
        vertexBuffer.add(new Vertex(1,0,0.2,new Col(1.,0.,0)));//v4
        vertexBuffer.add(new Vertex(0.8,-0.2,0.2,new Col(1,0,0.)));//v5

        getPartList().add(new Part(TopologyType.TRIANGLE, 0,1));
    }

}
