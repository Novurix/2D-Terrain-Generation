package Terrain.Trees;

import java.awt.Rectangle;
import java.awt.Graphics;

public class PineTree extends Rectangle {
    int branchY;
    int newDistance = 0;
    int branchWidth;

    public Rectangle[] branches = new Rectangle[15];
    
    public PineTree(int xCor, int yCor, int width) {
        setBounds(xCor,yCor,width,100);
        createBranches(xCor, yCor,width);
    }

    void createBranches(int x, int y, int treeWidth) {
        branchY = y;
        branchWidth = 10;
        for (int i = 0; i < branches.length; i++) {
            newDistance = (i+1)*2;
            branchY += 4;
            branchWidth = 10 + ((i+1)*(i+1))/20;

            branches[i] = new Rectangle();
            branches[i].setBounds(x+(treeWidth/2)-(branchWidth/2), branchY, branchWidth, 4);
        }
    }

    public void renderTrunk(Graphics graphics) {
        graphics.fillRect(x, y, width, height);
    }

    public void renderBranches(Graphics graphics, int index) {
        Rectangle branch = branches[index];
        graphics.fillRect(branch.x, branch.y, branch.width, branch.height);
    }

    public void update() {
        this.x-=2;
        for (int i = 0; i < branches.length; i++) {
            if (branches[i] != null) {
                branches[i].x -= 2;
            }
        }
    }
}