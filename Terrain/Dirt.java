package Terrain;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Dirt extends Rectangle {
    public Dirt(int xCor, int yCor, int width, int height) {
        setBounds(xCor, yCor+10,width,height);
    }

    public void render(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }

    public void update() {
        this.x-=2;
    }
}