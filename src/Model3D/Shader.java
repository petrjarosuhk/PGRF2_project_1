package Model3D;

import model.Vertex;
import transforms.Col;

@FunctionalInterface
public interface Shader {
    Col shade(Vertex v);

}

