package com.lbozo.AcidRun;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.beans.SerializedMap;
import com.mapper.beans.Waypoint;

public class Game extends JFrame implements Runnable {
  public float stability = 0.f;

  private static final long serialVersionUID = 1L;
  public int mapWidth = 63;
  public int mapHeight = 63;
  private Thread thread;
  private boolean running;
  private BufferedImage image;
  public int[] pixels;
  public Screen screen;
  private ArrayList<Texture> textures;

  public Camera camera;

  public static Texture wood = new Texture("redbrick.png", 64);
  public static Texture brick = new Texture("barrel.png", 64);
  public static Texture bluestone = new Texture("wall.png", 64);
  public static Texture stone = new Texture("wall.png", 64);

  public static final String MAP_DIR = "map/src/main/resources/static/maps/";

  public static int[][] map = new int[64][64];
  public static SerializedMap sMap;

  public void loadMap(File file) {
    try {
      sMap = getMap(file);
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    for(int i = 0; i < sMap.getToolData().getBrushes()[0].getPoints().getX().length; i++) {
      for(int j = 0; j < sMap.getToolData().getBrushes()[0].getPoints().getY()[i].length; j++) {
        int x = (int) sMap.getToolData().getBrushes()[0].getPoints().getX()[i][j];
        int y = (int) sMap.getToolData().getBrushes()[0].getPoints().getY()[i][j];

        map[x][y] = 1;
      }
    }

    // System.out.println(Arrays.deepToString(map));

    for (Waypoint w : sMap.getToolData().getWaypoints()) {
      map[(int)w.getX()][(int)w.getY()] = 2;
    }
  }

  private SerializedMap getMap(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
    ObjectMapper om = new ObjectMapper();
    return om.readValue(file, SerializedMap.class);
  }

  public Game() {
    reset();
  }

  public void reset() {
    JFileChooser fileChooser = new JFileChooser(MAP_DIR);
    fileChooser.showDialog(this, "Open Map");
    File file = fileChooser.getSelectedFile();
    loadMap(file);
    thread = new Thread(this);
    image = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
    pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    setSize(1280, 720);
    setResizable(false);
    setTitle("Acid Run");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBackground(Color.black);
    setLocationRelativeTo(null);
    setVisible(true);
    camera = new Camera(1.5, 1.5, 1, 0, 0, -.66, this);
    addKeyListener(camera);
    // System.out.println(Arrays.deepToString(map));
    textures = new ArrayList<Texture>();
    textures.add(wood);
    textures.add(brick);
    textures.add(bluestone);
    textures.add(stone);
    screen = new Screen(map, mapWidth, mapHeight, textures, 1280, 720);
    start();
  }

  private synchronized void start() {
    running = true;
    thread.start();
  }

  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics g = bs.getDrawGraphics();
    g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    bs.show();
  }

  public void update() {
    Waypoint w = sMap.getToolData().getWaypoints()[0];
    Graphics g = image.getGraphics();
    if (camera.xPos >= w.getX() && camera.xPos <= w.getX() - 1 && camera.yPos >= w.getX() && camera.yPos <= w.getX() - 1) {
      g.setColor(new Color(0,0,0,.2f));
      g.fillRect(0, 0, image.getWidth(), image.getHeight());
    }
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 1) {
          g.setColor(Color.white);
          g.fillRect(i * 16, j * 16, 16, 16);
        }
      }
    }

    g.setColor(Color.red);
    g.fillRect((int)w.getX() * 16, (int)w.getY() * 16, 16, 16);
    g.setColor(Color.blue);
    g.fillRect((int)camera.xPos * 16, ((int)camera.yPos * 16) + 16, 16, 16);
  }

  public void run() {
    long lastTime = System.nanoTime();
    final double ns = 1000000000.0 / 60.0;// 60 times per second
    double delta = 0;
    requestFocus();
    while (running) {
      long now = System.nanoTime();
      delta = delta + ((now - lastTime) / ns);
      lastTime = now;
      while (delta >= 1) {
        screen.update(camera, pixels);
        camera.update(map);
        update();
        delta--;
      }
      render();// displays to the screen unrestricted time
    }
  }
}
