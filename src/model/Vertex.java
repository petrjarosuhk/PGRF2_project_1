package model;

import transforms.*;

import java.util.Optional;

public class Vertex {
    private Point3D position;
    private Col color;
    private Vec3D normal;
    private Vec2D texture ;
    double one;


    public Vertex(double x, double y, double z, Col color) {
        position = new Point3D(x, y, z, 1);
        normal =  new Vec3D(1,1,1);
        texture = new Vec2D(1,1);
        this.color = color;
        one = 1;
    }

    public Vertex(Vertex v) {
        position = v.position;
        color = v.color;
        normal = v.normal;
        texture = v.texture;
        one = v.one;
    }

    public Col getColor() {
        return color;
    }

    public void setPosition(Vec3D v) {
        position = new Point3D(v.getX(), v.getY(), v.getZ(), position.getW());
    }
    public Point3D getPosition() {
        return position;
    }

    public double getOne() {
        return one;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "position=" + position +
                ", color=" + color +
                ", normal=" + normal +
                ", texture=" + texture +
                ", one=" + one +
                '}';
    }

    public Vec3D getNormal() {
        return normal;
    }

    public Vec2D getTexture() {
        return texture;
    }



    public Vertex mul(Mat4 transform) {
        return new Vertex(position.mul(transform),color,texture,normal,one);
    }

    public Vertex(Point3D p, Col col, Vec2D texture, Vec3D normal, double one) {
        this.position = p;
        this.color = col;
        this.normal = normal;
        this.texture = texture;
        this.one = one;
    }


    public Vertex add(Vertex v) {
        return new Vertex(
                position.add(v.getPosition()),
                color.add(v.getColor()),
                texture.add(v.getTexture()),
                normal.add(v.getNormal()),
                one + v.getOne()
        );
    }

    public Vertex mul(double d) {
        return new Vertex(
                position.mul(d),
                color.mul(d),
                texture.mul(d),
                normal.mul(d),
                one * d
        );
    }

    public Optional<Vertex> dehomog() {
        if (position.getW() == 0.)
            return Optional.empty();
        return Optional.of( new Vertex(
                new Point3D(position.getX() / position.getW(), position.getY() / position.getW(), position.getZ() / position.getW(), 1.0),
                new Col(color.getR() / position.getW(), color.getG() / position.getW(), color.getB() / position.getW()),
                new Vec2D(texture.getX() / position.getW(), texture.getY() / position.getW()),
                new Vec3D(normal.getX() / position.getW(), normal.getY() / position.getW(), normal.getZ() / position.getW()),
                one / position.getW()
        ));
    }


}
