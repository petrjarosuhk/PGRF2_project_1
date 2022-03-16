package renderer;

import Model3D.Scene;
import Model3D.Solid;
import model.Part;
import model.Vertex;
import transforms.Mat4;
import transforms.Mat4Identity;

/*Třída pro renderer daného objektu.*/

public class Renderer {
    private TriangleRasterizer triangleRasterizer;
    Mat4 viewMatrix = new Mat4Identity();
    Mat4 projectionMatrix = new Mat4Identity();

    public Renderer(TriangleRasterizer triangleRasterizer) {
        this.triangleRasterizer = triangleRasterizer;
    }

    public void render(Scene scene) {
        for (Solid s : scene.getSolidList()
        ) {
            render(s);
        }
    }


    public void render(Solid solid) {
        Mat4 trans = solid.getModelMatrix().mul(viewMatrix.mul(projectionMatrix));
        for (Part part : solid.getPartList()
        ) {
            int start;
            switch (part.getType()) {
                case LINES:
                    start = part.getIndexStart();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexV1 = start;
                        int indexV2 = start + 1;
                        Vertex v1 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV1));
                        Vertex v2 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV2));
                        v1 = v1.mul(trans);
                        v2 = v2.mul(trans);

                        triangleRasterizer.rasterize(v1,v2);
                        start += 2;
                    }
                    break;
                    //pokud bude triangle wire jakož to drátěný model tak se použije toto
                case TRIANGLE_WIRE:
                    start = part.getIndexStart();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexV1 = start;
                        int indexV2 = start + 1;
                        int indexV3 = start + 2;
                        Vertex v1 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV1));
                        Vertex v2 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV2));
                        Vertex v3 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV3));


                        v1 = v1.mul(trans);
                        v2 = v2.mul(trans);
                        v3 = v3.mul(trans);

                        //metoda rasterizeWire pro rasterizci WireFramu daného objektu
                        triangleRasterizer.rasterizeWire(
                                    new Vertex(v1.mul(1 / v1.getOne())),
                                    new Vertex(v2.mul(1 / v2.getOne())),
                                    new Vertex(v3.mul(1 / v3.getOne()))
                        );
                        start += 3;
                    }
                    break;
                case TRIANGLE:
                    start = part.getIndexStart();
                    for (int i = 0; i < part.getCount(); i++) {
                        int indexV1 = start;
                        int indexV2 = start + 1;
                        int indexV3 = start + 2;
                        Vertex v1 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV1));
                        Vertex v2 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV2));
                        Vertex v3 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(indexV3));


                        v1 = v1.mul(trans);
                        v2 = v2.mul(trans);
                        v3 = v3.mul(trans);

                        triangleRasterizer.rasterize(
                                new Vertex(v1.mul(1 / v1.getOne())),
                                new Vertex(v2.mul(1 / v2.getOne())),
                                new Vertex(v3.mul(1 / v3.getOne()))
                        );
                        start += 3;
                    }
                    break;
                case POINTS:
                    start = part.getIndexStart();
                            for(int i = 0;i<part.getCount();i++){
                                Vertex v1 = solid.getVertexBuffer().get(solid.getIndexBuffer().get(start));
                                v1 = v1.mul(trans);
                                triangleRasterizer.rasterize(v1);
                                start++;
                            }
                    break;

            }

        }
    }

    public void setProjectionMatrix(Mat4 projection) {
        projectionMatrix = projectionMatrix.mul(projection);
    }

    public void setView(Mat4 view) {
        viewMatrix = viewMatrix.mul(view);
    }


}
