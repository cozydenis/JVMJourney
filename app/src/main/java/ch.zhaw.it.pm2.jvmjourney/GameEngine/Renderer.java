package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

public class Renderer{

    Canvas canvas;
    GraphicsContext context;

    Image background;

    List<GameObject> entities = new ArrayList<>();

    public List<GameObject> getEntities() {
        return entities;
    }

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    public void addObject(GameObject Object) {
        entities.add(Object);
    }

    public void removeObject(GameObject Object) {
        entities.remove(Object);
    }

    public void clearEntities() {
        entities.clear();
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void render() {
        context.save();

        if(background!=null){
            context.drawImage(background, 0, 0);
        }

        for (GameObject Object : entities) {

            transformContext(Object);

            Point2D pos = Object.getDrawPosition();
            context.drawImage(
                    Object.getImage().getImage(),
                    pos.getX(),
                    pos.getY(),
                    Object.getWidth(),
                    Object.getHeight()
            );
        }

        context.restore();
    }

    public void prepare(){
        context.setFill( new Color(0.68, 0.68, 0.68, 1.0) );
        context.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
    }

    private void transformContext(GameObject Object){
        Point2D centre = Object.getCenter();
        Rotate r = new Rotate(Object.getRotation(), centre.getX(), centre.getY());
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
