package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

public class Pyramid extends Solid{

    public Pyramid(){

        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);
        indexBuffer.add(3);
        indexBuffer.add(4);
        indexBuffer.add(5);
        indexBuffer.add(6);
        indexBuffer.add(7);
        indexBuffer.add(8);
        indexBuffer.add(9);
        indexBuffer.add(10);
        indexBuffer.add(11);
        indexBuffer.add(12);
        indexBuffer.add(13);
        indexBuffer.add(14);
        indexBuffer.add(15);
        indexBuffer.add(16);
        indexBuffer.add(17);
        indexBuffer.add(18);
        indexBuffer.add(19);
        indexBuffer.add(20);
        indexBuffer.add(21);

        vertexBuffer.add(new Vertex(0,0,0,new Col(0,0,1.)));//v2
        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(0.,0.,0)));//v4

        vertexBuffer.add(new Vertex(0,0,0,new Col(1,0,0.)));//v2
        vertexBuffer.add(new Vertex(0,0.3,0,new Col(1,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(1.,0.,0)));//v4

        vertexBuffer.add(new Vertex(0,0,0,new Col(1,0,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.15,0.15,0.3,new Col(1.,0.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1,0,1.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(1.,1.,0)));//v3
        vertexBuffer.add(new Vertex(0.15,0.15,0.3,new Col(0.,0.,1)));//v4

        vertexBuffer.add(new Vertex(0,0,0,new Col(1,0,0.)));//v2
        vertexBuffer.add(new Vertex(0,0.3,0,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.15,0.15,0.3,new Col(1,0,0.)));//v2

        vertexBuffer.add(new Vertex(0,0.3,0,new Col(1.,0.,0)));//v4
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(1,1,1.)));//v5
        vertexBuffer.add(new Vertex(0.15,0.15,0.3,new Col(1.,0.,0)));//v4



        getPartList().add(new Part(TopologyType.TRIANGLE,0,6));

    }

}
