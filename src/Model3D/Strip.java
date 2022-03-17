package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Col;

/*Třída s implementací strip pro dva trojůhelníky */
public class Strip extends Solid{

    public Strip(){


        indexBuffer.add(0);
        indexBuffer.add(1);
        indexBuffer.add(2);

        indexBuffer.add(3);
        indexBuffer.add(4);
        indexBuffer.add(5);


        vertexBuffer.add(new Vertex(1,1,0.5,new Col(1.,0.,0)));//v3
        vertexBuffer.add(new Vertex(1,0.5,0.5,new Col(1.,0.,0)));//v4
        vertexBuffer.add(new Vertex(1,1,1,new Col(1,1,0.)));//v2

        vertexBuffer.add(new Vertex(1.5,1.5,0,new Col(0.,0.,1)));//v3

        getPartList().add(new Part(TopologyType.TRIANGLE_STRIP, 0,4)); // počet bodů je count složen ze čtyř bodů
    }

}
