package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

public class Blocks{
	private Controller controller;
	public LinkedList<Point> body = new LinkedList<Point>();
	boolean a = true;
	private boolean b ;
	final int MOVETIME =600;	
	
	public Blocks() {
		init();
	}

	public void init(){
		for(int x=Global.WIDTH/2;x < Global.WIDTH/2+2 ; x++){
			body.add(new Point(x,-2));
			body.add(new Point(x,-3));
		}
	}
	
	public void move(){
		System.out.println("block move ");
		for(Point p : body){
			p.y++;
		}		
		positionChanged();
	}
		
	public void changePositionUP(){
		System.out.println("change positionUP");
		positionChanged();
	}
	
	public void changePositionR(){
		System.out.println("change positionR");		
		b = true;
		for(Point p : body){
			if(p.x >= Global.WIDTH){
				b = false;
				break;
			}
		}
		if(b){
			for(Point p : body){
				p.x++;
			}			
			positionChanged();			
		}
	}
	
	public void changePositionL(){
		System.out.println("change positionL");
		b = true;
		for(Point p : body){
			if(p.x <= 0){
				b = false;
				break;				
			}
		}
		if(b){
			for(Point p : body){
				p.x--;
			}
			positionChanged();
		}			
	}
	
	public void speedUp(){
		System.out.println("speed up");
		b = true;
		for(Point p : body){
			if(p.y >=Global.HEIGTH){
				b=false;
				break;
			}
		}
		if(b){
			for(Point p : body){
				p.y++;
			}
			positionChanged();
		}			
	}
	
	public void positionChanged(){
		controller.isBlockHitBottom();
		System.out.println("Positionchanged");
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("drawMe blocks");
		g.setColor(Color.blue);
		for(Point p : body){
			g.fill3DRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE ,Global.CELL_SIZE,Global.CELL_SIZE, true);
		}
	}	
	
	private class BlocksDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
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

	public void addController(Controller controller){
		this.controller = controller;
	}
}
