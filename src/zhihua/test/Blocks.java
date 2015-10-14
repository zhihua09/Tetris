package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

public class Blocks{
	//private Controller controller;
	Bottom bottom;
	int x = 5;
	int y =-1;
	boolean a = true;
	final int MOVETIME =800;
	
	public void move(){
		System.out.println("block move ");
		y++;
		changePosition();
	}
		
	public void changePositionR(){
		System.out.println("change positionR");		
		x++;
		changePosition();
	}
	public void changePositionUP(){
		System.out.println("change positionUP");
		changePosition();
			
	}
	public void changePositionL(){
		System.out.println("change positionL");
		x--;
		changePosition();
	}
	
	public void speedUp(){
		System.out.println("speed up");
		y++;
		changePosition();
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("drawMe blocks");
		g.setColor(Color.blue);
		g.fill3DRect(x*25, y*25 ,25,25, true);
	}
	
	public void changePosition(){
//		isHit();	
		bottom.isBlockHitBottom();
		System.out.println("changePosition");
	}
	
//	public void isHit(){		
//	}
	
	private class BlocksDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//controller.blockMoved();
			while(a){
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
	
	public void addBottom(Bottom bottom){
		this.bottom = bottom;
	}
//	public void addBlockListener(Controller controller){
//		this.controller = controller;
//	}
}
