package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Blocks{
	private Controller controller;
	public ArrayList<Point> body = new ArrayList<Point>();
	boolean isGameOver = false;
	private boolean b ;
	final int MOVETIME =600;
	public boolean isTransfrom ;
	
	public Blocks() {
		isTransfrom = true;
//		init2();
		double x = Math.random();
		if(x>=0 && x<0.19)
			init7();
		else if(x>=0.2 && x< 0.39)
			init5();
		else if(x >= 0.4 && x<0.59)
			init3();
		else if(x >= 0.6 && x< 0.69)
			init4();
		else if(x>=0.7 && x< 0.79)
			init1();
		else if(x>= 0.8 && x< 0.89)
			init6();
		else init2();
	}
	
	public void init1(){//正方形
		isTransfrom = false;				
		for(int x=Global.WIDTH/2;x < Global.WIDTH/2+2 ; x++){
			body.add(new Point(x,-2));
			body.add(new Point(x,-3));			
		}
	}
	
	 public void init2(){//长条形
		 for(int x= Global.WIDTH/2-1 ; x < Global.WIDTH/2 + 3 ; x++){
			 body.add(new Point(x,-2));
		 }
	 }
	 
	 public void init3(){
		 for(int x = Global.WIDTH/2 - 1; x< Global.WIDTH/2 +2 ; x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2,-2));			 
	 }
	 
	 public void init4(){
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 +1,-2));
	 }
	 
	 public void init5(){
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 -1,-2));
	 }
	 
	 public void init6(){
		 body.add(new Point(Global.WIDTH/2-1,-3));
		body.add(new Point(Global.WIDTH/2,-3));
		body.add(new Point(Global.WIDTH/2 ,-2));
		body.add(new Point(Global.WIDTH/2 + 1 ,-2));
	 }
	 
	 public void init7(){
		 body.add(new Point(Global.WIDTH/2 + 1,-3));
		 body.add(new Point(Global.WIDTH/2 ,-3));
		 body.add(new Point(Global.WIDTH/2 -1 ,-2));
		 body.add(new Point(Global.WIDTH/2 ,-2));
	 }
	
	public void move(){
//		System.out.println("block move ");
		for(Point p : body){
			p.y++;
		}		
		positionChanged();
	}
		
	public void changePositionUP() {
		ArrayList<Point> body_bk = new ArrayList<Point>();
		synchronized(this){			
			for(Point p: body){				
				body_bk.add(new Point(p.x,p.y));
			}
		}
		Point o = body_bk.get(1);
		boolean isBottomOrWall = false;
		for(Point p: body_bk){
			int distance_y = o.x - p.x;
			int distance_x = o.y - p.y;
			int x = o.x + distance_x;
			int y = o.y - distance_y;
			if(controller.isBottomOrWall(x,y)){
				isBottomOrWall = true;				
				break;
			}							
			p.x = x;
			p.y = y;			
		}
		if(isBottomOrWall==false){			
			body = body_bk;
			System.out.println("change positionUP");
			positionChanged();
		}
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
//		System.out.println("Positionchanged");
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
//		System.out.println("drawMe blocks");
		g.setColor(Color.blue);
		for(Point p : body){
			g.fill3DRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE ,Global.CELL_SIZE,Global.CELL_SIZE, true);
		}
	}	
	
	private class BlocksDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!isGameOver){
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
