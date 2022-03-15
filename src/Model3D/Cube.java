package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
import transforms.Mat4Transl;

/*Třída s implementací index a vertex bufferu pro Cube*/
public class Cube extends Solid{

    public Cube(){

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
        indexBuffer.add(22);
        indexBuffer.add(23);
        indexBuffer.add(24);
        indexBuffer.add(25);
        indexBuffer.add(26);
        indexBuffer.add(27);
        indexBuffer.add(28);
        indexBuffer.add(29);
        indexBuffer.add(30);
        indexBuffer.add(31);
        indexBuffer.add(32);
        indexBuffer.add(33);
        indexBuffer.add(34);
        indexBuffer.add(35);

        vertexBuffer.add(new Vertex(0.3,0,0,new Col(0,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.6,0,0,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0.3,0,new Col(1.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(0.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0.3,0,new Col(1.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0.3,new Col(0,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.6,0,0.3,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0.3,0.3,new Col(1.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0.3,new Col(0,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0.3,new Col(0.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0.3,0.3,new Col(0.,1.,1)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0.3,new Col(0,1,1.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0.3,new Col(0.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.3,0,0,new Col(0,1,0.)));//v2

        vertexBuffer.add(new Vertex(0.6,0.3,0,new Col(1.,1.,0)));//v4
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(0,1,0.)));//v5
        vertexBuffer.add(new Vertex(0.6,0.3,0.3,new Col(1.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0,0,new Col(0,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.6,0,0,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0,0.3,new Col(0.,1.,1)));//v4


        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(0.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.3,0.3,0.3,new Col(1.,1.,0)));//v4


        vertexBuffer.add(new Vertex(0.3,0,0,new Col(1,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0,0.3,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0,0.3,new Col(0.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.3,0.3,0,new Col(1,1,0.)));//v2
        vertexBuffer.add(new Vertex(0.3,0.3,0.3,new Col(1.,1.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0.3,0.3,new Col(1.,1.,0)));//v4

        vertexBuffer.add(new Vertex(0.6,0,0,new Col(1,0,1.)));//v2
        vertexBuffer.add(new Vertex(0.6,0.3,0,new Col(1.,0.,1)));//v3
        vertexBuffer.add(new Vertex(0.6,0,0.3,new Col(1.,0.,1)));//v4

        vertexBuffer.add(new Vertex(0.6,0,0.3,new Col(1,0,1)));//v2
        vertexBuffer.add(new Vertex(0.6,0.3,0.3,new Col(1.,0.,1)));//v4
        vertexBuffer.add(new Vertex(0.6,0.3,0,new Col(1.,0.,1)));//v3
        getPartList().add(new Part(TopologyType.TRIANGLE,0,12));

    }

}
