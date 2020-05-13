import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Terrain.*;
import Terrain.Trees.*;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Launcher {
    public static void main(String[] args) {
        new Window();
    }
}

class Window extends JFrame {
    public Window() {
        setTitle("Terrain Generation");
        setSize(1100, 720);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100,720));

        addRenderer();
    }

    void addRenderer() {

        setLayout(new GridLayout(1, 1, 0, 0));

        add(new Renderer(this));
        setVisible(true);
    }
}

class Renderer extends JPanel implements ActionListener, KeyListener {

    int defaultHeight = 500;
    int height = defaultHeight;
    int partSizeX = 10;

    int loopFor;

    Grass[] grass;
    Dirt[] dirt;
    Stone[] stone;
    PineTree[] trees;

    Timer timer = new Timer(10, this);

    public Renderer(Window window) {

        loopFor = 10000;

        grass = new Grass[loopFor];
        stone = new Stone[loopFor];
        dirt = new Dirt[loopFor];
        trees = new PineTree[loopFor];

        setFocusable(true);
        addKeyListener(this);

        timer.start();

        createTerrain();
    }

    void createTerrain() {
        for (int i = 0; i < loopFor; i++) {

            Random randomH = new Random();
            Random randomO = new Random();

            Random randomT = new Random();
            Random randomDH = new Random();

            int randomHeight = randomH.nextInt(4);   
            int randomOp = randomO.nextInt(2);
            int randomTree = randomT.nextInt(100);
            int randomDirtHeight = randomDH.nextInt(10);

            if (randomOp == 0) {
                height += randomHeight * 2;
                grass[i] = new Grass(i * partSizeX, height, partSizeX);
                stone[i] = new Stone(i * partSizeX, height, partSizeX);
                dirt[i] = new Dirt(i * partSizeX, height, partSizeX,randomDirtHeight+30);
            } else {
                height -= randomHeight * 2;
                grass[i] = new Grass(i * partSizeX, height, partSizeX);
                stone[i] = new Stone(i * partSizeX, height, partSizeX);
                dirt[i] = new Dirt(i * partSizeX, height, partSizeX,randomDirtHeight+30);
            }

            if (randomTree+1 <= 30) {
                Random random_H = new Random();
                int randomTreeHeight = random_H.nextInt(50);
                trees[i] = new PineTree(grass[i].x,grass[i].y-(randomTreeHeight+41),6);
            }
        }       
    }
    

    public void paint(Graphics graphics) {

        Graphics treeTrunk = graphics;
        treeTrunk.setColor(new Color(64, 35, 10));

        for (int i = 0; i < trees.length; i++) {
            if (trees[i] != null) {
                trees[i].renderTrunk(treeTrunk);
            }
        }

        for (int i = 0; i < trees.length; i++) {
            if (trees[i] != null) {

                for (int j = 0; j < trees[i].branches.length; j++) {
                    if (trees[i].branches[j] != null) {

                        Graphics treeLeaves = graphics;
                        treeLeaves.setColor(new Color(0, 128, 11));
                        trees[i].renderBranches(treeLeaves, j);
                    }
                }
            }
        }

        Graphics rockGraphics = graphics;
        rockGraphics.setColor(new Color(148, 148, 148));

        for (int i = 0; i < stone.length; i++) {
            if (stone[i] != null) {
                stone[i].render(rockGraphics);
            }
        }


        Graphics dirtGraphics = graphics;
        dirtGraphics.setColor(new Color(84, 43, 17));

        for (int i = 0; i < dirt.length; i++) {
            if (dirt[i] != null) {
                dirt[i].render(dirtGraphics);
            }
        }

        Graphics grassGraphics = graphics;
        grassGraphics.setColor(new Color(17, 163, 10));

        for (int i = 0; i < grass.length; i++) {
            if (grass[i] != null) {
                grass[i].render(grassGraphics);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        for (int i = 0; i < loopFor; i++) {
            if (stone[i] != null) {
                stone[i].update();
            }
            if (dirt[i] != null) {
                dirt[i].update();
            }
            if (grass[i] != null) {
                grass[i].update();
            }

            if (trees[i] != null) {
                trees[i].update();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            
            for (int i = 0; i < loopFor; i++) {
                stone[i] = null;
                dirt[i] = null;
                grass[i] = null;
                trees[i] = null;
            }
            height = defaultHeight;     
            createTerrain();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}