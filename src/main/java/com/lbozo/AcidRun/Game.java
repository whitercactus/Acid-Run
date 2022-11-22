package com.lbozo.AcidRun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.beans.*;

public class Game extends JFrame implements Runnable {
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

  public static Texture wood = new Texture("wall.png", 64);
  public static Texture brick = new Texture("wall.png", 64);
  public static Texture bluestone = new Texture("wall.png", 64);
  public static Texture stone = new Texture("wall.png", 64);

  public static final String MAP_DIR = "map/src/main/resources/static/maps/";

  public static int[][] map = new int[64][64];

  private void loadMap(String fileName) {
    SerializedMap sMap;
    try {
      sMap = getMap(MAP_DIR + fileName);
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    System.out.println(sMap);

    for(int i = 0; i < sMap.toolData.brushes[0].points.x.length; i++) {
      for(int j = 0; j < sMap.toolData.brushes[0].points.x[i].length; j++) {
        int x = (int) sMap.toolData.brushes[0].points.x[i][j];
        int y = (int) sMap.toolData.brushes[0].points.y[i][j];

        map[(int)x][(int)y] = 1;
      }
    }

    System.out.println(Arrays.deepToString(map));
  }

  private SerializedMap getMap(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
    FileInputStream fs = new FileInputStream(fileName);
    ObjectInputStream os = new ObjectInputStream(fs);

    SerializedMap serMap = (SerializedMap) os.readObject();

    fs.close();
    os.close();

    return serMap;
  }

  public Game() {
    loadMap("1.sr");
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
    camera = new Camera(1.5, 1.5, 1, 0, 0, -.66);
    addKeyListener(camera);
    System.out.println(Arrays.deepToString(map));
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
        delta--;
      }
      render();// displays to the screen unrestricted time
    }
  }
}
