package control;

import Model3D.*;
import raster.ImageBuffer;
import raster.ZbufferVisibility;
import renderer.Renderer;
import renderer.TriangleRasterizer;
import transforms.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;

public class Controller3D implements Controller {

    private final Panel panel;

    private int width, height;
    private Camera camera = new Camera()
            .withPosition(new Vec3D(-0.36,-0.73,1.82))
            .withAzimuth(-4.58)
            .withZenith(-1.33)
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
    Triangle cube = new Triangle();
    Cube cube2 = new Cube();

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
                                panel.getHeight()/ (float) panel.getWidth(),
                                0.1,
                                30);
                        break;
                    case KeyEvent.VK_X:
                        ar.setModelmatrix(ar.getModelMatrix().mul(new Mat4RotX(0.1)));
                        break;
                    case KeyEvent.VK_Y:
                        ar.setModelmatrix(ar.getModelMatrix().mul(new Mat4RotY(0.1)));
                        break;
                    case KeyEvent.VK_Z:
                        ar.setModelmatrix(ar.getModelMatrix().mul(new Mat4RotZ(0.1)));
                        break;
                    case KeyEvent.VK_A:
                        modeCut++;
                        if(modeCut==3)
                            modeCut=0;
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
                        break;
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

    private void redraw3D() {

        triangleRasterizer.getzBuffer().getDbuffer().clear();//vyčistit si  buffer vždycky před každým kreslením

        triangleRasterizer.setModeCut(modeCut);
        renderer = new Renderer(triangleRasterizer);
        renderer.setProjectionMatrix(projection);
        renderer.setView(camera.getViewMatrix());

        renderer.render(ar);
        renderer.render(arrowZ);
        renderer.render(arrowY);
        renderer.render(axesX);
        renderer.render(cube);
        renderer.render(cube2);


       /*triangleRasterizer.rasterize(new Vertex(0.3,0.8,-0.1,new Col(0x00ff00)),
                new Vertex(-0.2,0.2,-0.5,new Col(0x00ffff)),
                new Vertex(0.6,-0.9,-0.9,new Col(0xffff00)));

        triangleRasterizer.rasterize(new Vertex(-0.96,0.2,-0.5,new Col(0x00ff00)),
                new Vertex(0.3,-0.2,-0.1,new Col(0xff0000)),
                new Vertex(0.66,0.5,-0.5,new Col(0x0000ff)));*/
    }


}
