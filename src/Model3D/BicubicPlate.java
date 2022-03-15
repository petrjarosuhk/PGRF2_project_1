package Model3D;

import model.Part;
import model.TopologyType;
import model.Vertex;
import transforms.Bicubic;
import transforms.Col;
import transforms.Cubic;
import transforms.Point3D;
/*Třída s implementací index a vertex bufferu s vzužitím Bicubici*/
public class BicubicPlate extends Solid{

    public BicubicPlate(){

        Bicubic bicubic = new Bicubic(Cubic.FERGUSON,
                new Point3D(1/2,1/2,0.2/2), new Point3D(1.3/2,1.3/2,1.3/2), new Point3D(1.3/2,1.3/2,1.3/2), new Point3D(1/2,1/2,0.2/2),
                new Point3D(0/2,0/2,1/2), new Point3D(1/2,1/2,2/2), new Point3D(4/2,1/2,2/2), new Point3D(2/2,3/2,1/2),
                new Point3D(2/2,3/2,1/2), new Point3D(1/2,4/2,2/2), new Point3D(4/2,4/2,2/2), new Point3D(3/2,3/2,1/2),
                new Point3D(2/2,3/2,1/2), new Point3D(1/2,4/2,2/2), new Point3D(4/2,4/2,2/2), new Point3D(3/2,3/2,1/2),
                new Point3D(1/2,1/2,0.2/2), new Point3D(1/2,4/2,1/2), new Point3D(4/2,4/2,1/2), new Point3D(1/2,1/2,0.2/2));

        for(int i = 0; i <4; i++)

            for(double j = 0; j < 1; j+=0.1){
                Point3D p = new Point3D(bicubic.compute(j,j));
                getVertexBuffer().add(new Vertex(p.getX(),p.getY(),p.getZ(), new Col(0.5,1,0.4)));
            }
            for(int i = 0; i <getVertexBuffer().size() - 1; i++){
                getIndexBuffer().add(i);
                getIndexBuffer().add(i + 1);
            }

        getPartList().add(new Part(TopologyType.LINES, 0, getVertexBuffer().size()-1));


    }

}
