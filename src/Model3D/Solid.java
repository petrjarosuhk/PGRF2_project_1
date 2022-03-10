package Model3D;

import model.Part;
import model.Vertex;
import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.ArrayList;
import java.util.List;

public abstract class Solid {
    protected List<Integer> indexBuffer = new ArrayList<>();
    protected List<Vertex> vertexBuffer = new ArrayList<>();
    protected List<Part> partList = new ArrayList<>();
    Mat4 modelmatrix = new Mat4Identity();

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }
    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }
    public List<Part> getPartList() {
        return partList;
    }

    public Solid() {
    }
    public Mat4 getModelMatrix() {
        return modelmatrix;
    }

    public void setModelmatrix(Mat4 modelmatrix) {
        this.modelmatrix = modelmatrix;
    }
}
