package com.lbozo.AcidRun;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Camera implements KeyListener {

	public double xPos, yPos, xDir, yDir, xPlane, yPlane;
	public boolean left, right, forward, back;
	public final double MOVE_SPEED = .08;
	public final double ROTATION_SPEED = .045;

	private Game game;

	public Camera(double x, double y, double xd, double yd,
			double xp, double yp, Game game) {
		xPos = x;
		yPos = y;
		xDir = xd;
		yDir = yd;
		xPlane = xp;
		yPlane = yp;
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if ((key.getKeyCode() == KeyEvent.VK_LEFT))
			left = true;
		if ((key.getKeyCode() == KeyEvent.VK_RIGHT))
			right = true;
		if ((key.getKeyCode() == KeyEvent.VK_UP))
			forward = true;
		if ((key.getKeyCode() == KeyEvent.VK_DOWN))
			back = true;
	}

	@Override
	public void keyReleased(KeyEvent key) {
		if ((key.getKeyCode() == KeyEvent.VK_LEFT))
			left = false;
		if ((key.getKeyCode() == KeyEvent.VK_RIGHT))
			right = false;
		if ((key.getKeyCode() == KeyEvent.VK_UP))
			forward = false;
		if ((key.getKeyCode() == KeyEvent.VK_DOWN))
			back = false;
	}

	@Override
	public void keyTyped(KeyEvent key) {
		System.out.println(KeyEvent.KEY_TYPED);
		if(KeyEvent.KEY_TYPED == 400) {
			game.reset();

			//replace with this for an acid trip when hot swapping maps

//			JFileChooser fileChooser = new JFileChooser(Game.MAP_DIR);
//			fileChooser.showDialog(game, "Open Map");
//			File file = fileChooser.getSelectedFile();
//			game.loadMap(file);
//			xPos = 1.5;
//			yPos = 1.5;
//			xDir = 1;
//			yDir = 0;
//			xPlane = -.66;
		}
	}

	public void update(int[][] map) {
		if (forward) {
			if (map[(int)(xPos + xDir * MOVE_SPEED)][(int)yPos] == 0) {
				xPos+=xDir*MOVE_SPEED;
			}
			if (map[(int)xPos][(int)(yPos + yDir * MOVE_SPEED)] == 0)
				yPos+=yDir*MOVE_SPEED;
		}
		if (back) {
			if (map[(int)(xPos - xDir * MOVE_SPEED)][(int)yPos] == 0)
				xPos-=xDir*MOVE_SPEED;
			if (map[(int)xPos][(int)(yPos - yDir * MOVE_SPEED)] == 0)
				yPos-=yDir*MOVE_SPEED;
		}
		if (right) {
			double oldxDir=xDir;
			xDir=xDir*Math.cos(-ROTATION_SPEED) - yDir*Math.sin(-ROTATION_SPEED);
			yDir=oldxDir*Math.sin(-ROTATION_SPEED) + yDir*Math.cos(-ROTATION_SPEED);
			double oldxPlane = xPlane;
			xPlane=xPlane*Math.cos(-ROTATION_SPEED) - yPlane*Math.sin(-ROTATION_SPEED);
			yPlane=oldxPlane*Math.sin(-ROTATION_SPEED) + yPlane*Math.cos(-ROTATION_SPEED);
		}
		if (left) {
			double oldxDir=xDir;
			xDir=xDir*Math.cos(ROTATION_SPEED) - yDir*Math.sin(ROTATION_SPEED);
			yDir=oldxDir*Math.sin(ROTATION_SPEED) + yDir*Math.cos(ROTATION_SPEED);
			double oldxPlane = xPlane;
			xPlane=xPlane*Math.cos(ROTATION_SPEED) - yPlane*Math.sin(ROTATION_SPEED);
			yPlane=oldxPlane*Math.sin(ROTATION_SPEED) + yPlane*Math.cos(ROTATION_SPEED);
		}
	}
}

