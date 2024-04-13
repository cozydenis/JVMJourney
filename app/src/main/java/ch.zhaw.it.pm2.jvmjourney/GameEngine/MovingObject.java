package ch.zhaw.it.pm2.jvmjourney.GameEngine;

public class MovingObject extends Object {
    private int x;
    private int y;
    private int speed;

    public MovingObject(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void move() {
        x += speed;
        y += speed;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void move(int x, int y, int speed) {
        this.x += x * speed;
        this.y += y * speed;
    }



    public void move(int x, int y, int speed, int time) {
        this.x += x * speed * time;
        this.y += y * speed * time;
    }

    public void move(int x, int y, int speed, int time, int acceleration) {
        this.x += x * speed * time * acceleration;
        this.y += y * speed * time * acceleration;
    }






}
