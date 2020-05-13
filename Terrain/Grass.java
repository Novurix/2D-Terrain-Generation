package Terrain;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Grass extends Rectangle {
    public Grass(int xCor, int yCor, int width) {
        setBounds(xCor, yCor,width,20);
    }

    public void render(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }

    public void update() {
        this.x-=2;
    }
}