package Terrain;

import java.awt.Rectangle;
import java.awt.Graphics;

public class Stone extends Rectangle {

    public Stone(int xCor, int yCor, int width) {
        setBounds(xCor, yCor+10,width,2000);
    }

    public void render(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }

    public void update() {
        this.x-=2;
    }
}