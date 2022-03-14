package control;

import Model3D.*;
import raster.ImageBuffer;
import raster.ZbufferVisibility;
import renderer.Renderer;
import renderer.TriangleRasterizer;
import transforms.*;
import view.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static model.TopologyType.*;

public class Controller3D implements Controller {

    private final Panel panel;
    private boolean cubeEdit = false;
    private boolean triangleEdit = false;
    private boolean pyramidEdit = false;
    private boolean translationCube = false;
    private  boolean translationPyramid = false;
    private boolean rotationCube = false;
    private boolean rotationPyramid = false;
    private  boolean tranX = false;
    private boolean transY = false;
    private boolean transZ = false;
    private boolean translationTriangle = false;
    private boolean rotationTriangle = false;

    private int width, height;


    private Camera camera = new Camera()
            .withPosition(new Vec3D(1.8,0.6,1.6))
            .withAzimuth(-3.2)
            .withZenith(-0.7)
            .withFirstPerson(true);
    double camStep = 0.1;
    private ZbufferVisibility zbufferVisibility;
    private TriangleRasterizer triangleRasterizer;
    Renderer renderer;
    Mat4 projection = new Mat4Identity();
    Point2D oldPoint;
    int modeCut =0;
    ArrowXandTraingle ar = new ArrowXandTraingle();
    AxesY arrowY = new AxesY();
    AxesX axesX = new AxesX();
    AxesZ arrowZ = new AxesZ();
    Triangle triangle = new Triangle();
    Cube cube2 = new Cube();
   Pyramid pyramid = new Pyramid();
   BicubicPlate bicubicPlate = new BicubicPlate();
    Shader shader;


    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearValue(new Col(0xff0000));
        zbufferVisibility = new ZbufferVisibility(panel.getRaster());
        projection = new Mat4PerspRH(Math.PI / 3,
                panel.getHeight()/ (float) panel.getWidth(),
                0.1,
                30);



        shader = v -> {
            return v.getColor();
        };


       triangleRasterizer = new TriangleRasterizer(zbufferVisibility, shader);




    }

    @Override
    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                oldPoint= new Point2D(e.getX(),e.getY());

                //cube translace
                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {

                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(0.1, 0, 0)));
                        panel.clear();
                        redraw();


                }
                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON3)&& (tranX == true)) {

                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(-0.1, 0, 0)));
                    panel.clear();
                    redraw();
                }


                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {

                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(0, 0.1, 0)));
                    panel.clear();
                    redraw();
                }
                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {

                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(0, -0.1, 0)));
                    panel.clear();
                    redraw();
                }

                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {

                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(0, 0, 0.1)));
                    panel.clear();
                    redraw();
                }
                if ((cubeEdit == true) && (translationCube == true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {

                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4Transl(0, 0, -0.1)));
                    panel.clear();
                    redraw();
                }
                //pyramid translace


                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(0.1, 0, 0)));
                    panel.clear();
                    redraw();
                }
                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3)&& (tranX == true)) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(-0.1, 0, 0)));
                    panel.clear();
                    redraw();
                }


                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(0, 0.1, 0)));
                    panel.clear();
                    redraw();
                }
                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(0, -0.1, 0)));
                    panel.clear();
                    redraw();
                }

                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(0, 0, 0.1)));
                    panel.clear();
                    redraw();
                }
                if ((pyramidEdit == true) && (translationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {

                    pyramid.setModelmatrix(pyramid.getModelMatrix().mul(new Mat4Transl(0, 0, -0.1)));
                    panel.clear();
                    redraw();
                }




                //triangle rotation


                if ((triangleEdit == true) && (rotationTriangle == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotX(0.1)));
                        panel.clear();
                        redraw();
                    }
                }



                if ((triangleEdit == true) && (rotationTriangle == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotY(0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if ((triangleEdit == true) && (rotationTriangle == true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotZ(0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if ((triangleEdit == true) && (rotationTriangle == true) && (e.getButton() == MouseEvent.BUTTON3) && (tranX == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotX(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if ((triangleEdit == true) && (rotationTriangle == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotY(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if ((triangleEdit== true) && (rotationTriangle== true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {
                    {
                        triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4RotZ(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                //cube rotation

                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {
                    {
                    cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotX(0.1)));
                        panel.clear();
                        redraw();
                }
                }



                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {
                    {
                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotY(0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {
                    {
                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotZ(0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON3) && (tranX == true) ) {
                    {
                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotX(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {
                    {
                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotY(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if ((cubeEdit == true) && (rotationCube == true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {
                    {
                        cube2.setModelmatrix(cube2.getModelMatrix().mul(new Mat4RotZ(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                //pyramid rotation


                if ((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotX(0.1)));
                        panel.clear();
                        redraw();
                    }
                }



                if ((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotY(0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotZ(0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if ((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3) && (tranX == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotX(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }


                if  ((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotY(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }

                if  ((pyramidEdit== true) && (rotationPyramid == true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {
                    {
                        pyramid.setModelmatrix( pyramid.getModelMatrix().mul(new Mat4RotZ(-0.1)));
                        panel.clear();
                        redraw();
                    }
                }



                //triangle transla


                if ((triangleEdit == true) && (translationTriangle == true) && (e.getButton() == MouseEvent.BUTTON1) && (tranX == true) ) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(0.1, 0, 0)));
                    panel.clear();
                    redraw();
                }
                if ((triangleEdit == true) && (translationTriangle == true) && (e.getButton() == MouseEvent.BUTTON3)&& (tranX == true)) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(-0.1, 0, 0)));
                    panel.clear();
                    redraw();
                }


                if ((triangleEdit == true) && (translationTriangle == true) && (e.getButton() == MouseEvent.BUTTON1) && (transY == true) ) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(0, 0.1, 0)));
                    panel.clear();
                    redraw();
                }
                if ((triangleEdit == true) && (translationTriangle == true) && (e.getButton() == MouseEvent.BUTTON3) && (transY == true) ) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(0, -0.1, 0)));
                    panel.clear();
                    redraw();
                }

                if ((triangleEdit == true) && (translationTriangle== true) && (e.getButton() == MouseEvent.BUTTON1) && (transZ == true) ) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(0, 0, 0.1)));
                    panel.clear();
                    redraw();
                }
                if ((triangleEdit == true) && (translationTriangle == true) && (e.getButton() == MouseEvent.BUTTON3) && (transZ == true) ) {

                    triangle.setModelmatrix(triangle.getModelMatrix().mul(new Mat4Transl(0, 0, -0.1)));
                    panel.clear();
                    redraw();
                }





            }
        });

        panel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if(e.getWheelRotation() == -1) {

                    camera = camera.forward(camStep);
                    panel.clear();
                    redraw();
                }

                if(e.getWheelRotation() == 1) {

                    camera = camera.backward(camStep);
                    panel.clear();
                    redraw();
                }
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                panel.clear();
                double dx = (e.getX() - oldPoint.getX());
                double dy = (e.getY() - oldPoint.getY());
                double zenith = camera.getZenith() - (Math.PI * dy) / (panel.getHeight() - 1);
                if (zenith > Math.PI / 2) zenith = Math.PI / 2;
                if (zenith < -Math.PI / 2) zenith = -Math.PI / 2;
                camera = camera.withZenith(zenith);
                double azimuth = camera.getAzimuth() + ((Math.PI * dx) / (panel.getWidth() - 1));
                camera = camera.withAzimuth((azimuth % (Math.PI * 2)));
                oldPoint = new Point2D(e.getX(), e.getY());
                redraw();
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

        panel.requestFocus();
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Mat4 model;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        camera = camera.forward(camStep);
                        break;
                    case KeyEvent.VK_DOWN:
                        camera = camera.backward(camStep);
                        break;
                    case KeyEvent.VK_LEFT:
                        camera = camera.left(camStep);
                        break;
                    case KeyEvent.VK_RIGHT:
                        camera = camera.right(camStep);
                        break;
                    case KeyEvent.VK_D:
                        camera = camera.down(camStep);
                        break;
                    case KeyEvent.VK_U:
                        camera = camera.up(camStep);
                        break;
                    case KeyEvent.VK_O:
                        projection = new Mat4OrthoRH(3, 2, 0.1, 10);
                        break;
                    case KeyEvent.VK_I:
                        projection = new Mat4PerspRH(Math.PI / 3,
                                panel.getHeight() / (float) panel.getWidth(),
                                0.1,
                                30);
                        break;


                    case KeyEvent.VK_M:


                        panel.clear();
                        redraw();

                        break;
                    case KeyEvent.VK_N:
                                 {
                        if (cubeEdit == true){
                            int i = 0;
                        for (i = 0; i < cube2.getPartList().size(); i++) {
                            cube2.getPartList().get(i).setTopology(TRIANGLE_WIRE);
                                    }
                            }
                            if(triangleEdit == true) {
                                int i = 0;
                                for (i = 0; i < triangle.getPartList().size(); i++) {
                                    triangle.getPartList().get(i).setTopology(TRIANGLE_WIRE);
                                }
                            }
                            if(pyramidEdit == true) {
                                int i = 0;
                                for (i = 0; i < pyramid.getPartList().size(); i++) {
                                    pyramid.getPartList().get(i).setTopology(TRIANGLE_WIRE);
                                         }
                                     }
                        }
                        break;
                    case KeyEvent.VK_SHIFT:
                  cubeEdit = false;
                  triangleEdit = false;
                  pyramidEdit = false;
                  redraw();
                    break;
                    case KeyEvent.VK_L:
                    {
                        if (cubeEdit == true){
                            int i = 0;
                            for (i = 0; i < cube2.getPartList().size(); i++) {
                                cube2.getPartList().get(i).setTopology(TRIANGLE);
                            }
                        }
                        if(triangleEdit == true) {
                            int i = 0;
                            for (i = 0; i < triangle.getPartList().size(); i++) {
                                triangle.getPartList().get(i).setTopology(TRIANGLE);
                            }
                        }
                        if(pyramidEdit == true) {
                            int i = 0;
                            for (i = 0; i < pyramid.getPartList().size(); i++) {
                                pyramid.getPartList().get(i).setTopology(TRIANGLE);
                            }
                        }
                    }

                    triangleEdit = false;
                    cubeEdit=false;
                    redraw();
                    break;
                    case KeyEvent.VK_A:
                        modeCut++;
                        if (modeCut == 3)
                            modeCut = 0;
                        break;
                    case KeyEvent.VK_NUMPAD0:
                        shader = v -> {
                            return new Col(0, 1.0, 0);
                        };
                        break;
                    case KeyEvent.VK_NUMPAD1:
                        shader = v -> {
                            return new Col(1.0, 0, 0);
                        };

                        break;
                    case KeyEvent.VK_NUMPAD2:
                        shader = v -> {
                            return new Col(0, 0, 1.0);
                        };
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        shader = v -> {
                            return v.getColor();
                        };

                }

                triangleRasterizer = new TriangleRasterizer(zbufferVisibility,shader);
                panel.clear();


                redraw();



            }
        });
    }


    private void redraw() {

        width = panel.getRaster().getWidth();
        height = panel.getRaster().getHeight();
        Graphics g = panel.getRaster().getGraphics();

        g.setColor(Color.white);


        redraw3D();
        panel.repaint();
    }
private int trans = 0;
    private void redraw3D() {


        triangleRasterizer.getzBuffer().getDbuffer().clear();

        triangleRasterizer.setModeCut(modeCut);
        renderer = new Renderer(triangleRasterizer);
        renderer.setProjectionMatrix(projection);
        renderer.setView(camera.getViewMatrix());


        renderer.render(arrowZ);
        renderer.render(arrowY);
        renderer.render(axesX);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'c'){
                 cubeEdit = true;
                 pyramidEdit = false;
                 triangleEdit = false;
                }



                if(e.getKeyChar() == 'k'){
                    triangleEdit = true;
                    pyramidEdit = false;
                    cubeEdit = false;
                }
                if(e.getKeyChar() == 'h'){
                    pyramidEdit = true;
                    triangleEdit = false;
                    cubeEdit = false;

                }
                if(pyramidEdit == true)
                { if(e.getKeyChar() == 't') {
                    translationCube = false;
                    cubeEdit = false;
                    translationPyramid = true;


                }
                }


                if(triangleEdit == true)
                { if(e.getKeyChar() == 't') {
                    translationCube = false;
                    cubeEdit = false;
                    translationPyramid = false;
                    translationTriangle = true;

                }
                }
                if(pyramidEdit == true)
                { if(e.getKeyChar() == 'r') {
                    translationCube = false;
                    cubeEdit = false;
                    translationPyramid =false;
                    rotationPyramid = true;

                }
                }


                if(triangleEdit == true)
                { if(e.getKeyChar() == 'r') {
                    translationCube = false;
                    cubeEdit = false;
                    translationPyramid =false;
                    rotationPyramid =false;
                    rotationTriangle = true;

                }
                }

                if(rotationTriangle == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(rotationTriangle == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(rotationTriangle == true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }


                if(rotationPyramid == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(rotationPyramid == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(rotationPyramid == true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }


                if(translationPyramid == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(translationPyramid == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(translationPyramid== true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }

                if(cubeEdit == true)
                { if(e.getKeyChar() == 't') {
                    translationCube = true;
                    translationPyramid = false;
                }
                }

                if(translationCube == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(translationCube == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(translationCube == true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }

                if(cubeEdit == true)
                { if(e.getKeyChar() == 'r') {
                    translationCube = false;
                    translationPyramid = false;
                    rotationCube = true;
                }
                }

                if(rotationCube == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(rotationCube == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(rotationCube == true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }


                if(translationTriangle == true){
                    if(e.getKeyChar() == 'x') {
                        tranX = true;
                        transY = false;
                        transZ = false;
                    }
                }
                if(translationTriangle == true){
                    if(e.getKeyChar() == 'y') {
                        tranX = false;
                        transY = true;
                        transZ = false;
                    }
                }

                if(translationTriangle == true){
                    if(e.getKeyChar() == 'z') {
                        tranX = false;
                        transY = false;
                        transZ = true;
                    }
                }

            }
        });

       renderer.render(pyramid);
       renderer.render(triangle);
       renderer.render(cube2);
       renderer.render(bicubicPlate);


    }




}
