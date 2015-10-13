package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

public class Blocks{
	Controller controller;
	int x = 5;
	int y =-1;
	final int MOVETIME =800;
	
	public void move(){
		System.out.println("block move ");
		y++;
				
	}
		
	public void changePositionR(){
		System.out.println("change positionR");
		x++;		
	}
	public void changePositionUP(){
		System.out.println("change positionUP");
			
	}
	public void changePositionL(){
		System.out.println("change positionL");
		x--;		
	}
	
	public void speedUp(){
		System.out.println("speed up");
		y++;		
	}
	public void speedDown(){
		System.out.println("speed up");			
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("drawMe");
		g.setColor(Color.blue);
		g.fill3DRect(x*25, y*25 ,25,25, true);
	}
	
	private class BlocksDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			controller.blockMoved();
			while(true){
				move();								
				try {
					Thread.sleep(MOVETIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}				
		}		
	}
	
	public void start(){
		new Thread(new BlocksDrive()).start();
	}
	
	public void addBlockListener(Controller controller){
		this.controller = controller;
	}
}
