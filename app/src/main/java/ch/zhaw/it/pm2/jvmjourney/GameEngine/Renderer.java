package ch.zhaw.it.pm2.jvmjourney.GameEngine;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for rendering game objects onto a canvas.
 */
public class Renderer {

    Canvas canvas;
    GraphicsContext context;

    Image background;

    List<GameObject> entities = new ArrayList<>();

    /**
     * Constructs a Renderer with the specified canvas.
     *
     * @param canvas The canvas to render on.
     */
    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    /**
     * Retrieves the list of entities currently managed by the renderer.
     *
     * @return The list of entities.
     */
    public List<GameObject> getEntities() {
        return entities;
    }

    /**
     * Adds a game object to the list of entities to be rendered.
     *
     * @param object The game object to add.
     */
    public void addObject(GameObject object) {
        entities.add(object);
    }

    /**
     * Removes a game object from the list of entities to be rendered.
     *
     * @param object The game object to remove.
     */
    public void removeObject(GameObject object) {
        entities.remove(object);
    }

    /**
     * Clears all entities from the renderer.
     */
    public void clearEntities() {
        entities.clear();
    }

    /**
     * Sets the background image for the renderer.
     *
     * @param background The background image to set.
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    /**
     * Renders all entities onto the canvas.
     */
    public void render() {
        context.save();

        if (background != null) {
            context.drawImage(background, 0, 0);
        }

        for (GameObject object : entities) {
            transformContext(object);

            Point2D pos = object.getDrawPosition();
            context.drawImage(
                    object.getImage(),
                    pos.getX(),
                    pos.getY(),
                    object.getWidth(),
                    object.getHeight()
            );
        }

        context.restore();
    }

    /**
     * Prepares the canvas by filling it with a background color.
     */
    public void prepare() {
        context.setFill(new Color(0.68, 0.68, 0.68, 1.0));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Transforms the rendering context based on the properties of the given game object.
     *
     * @param object The game object to transform the context for.
     */
    private void transformContext(GameObject object) {
        Point2D centre = object.getCenter();
        Rotate r = new Rotate(object.getRotation(), centre.getX(), centre.getY());
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
