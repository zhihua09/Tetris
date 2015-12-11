package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Blocks{
	private Controller controller;
	public ArrayList<Point> body = new ArrayList<Point>();
	boolean canBlocksDrive = true;//积木是否下落
	final int MOVETIME =600;//下落速度
	public boolean isTransfrom ;//积木是否可以旋转
	
	/**
	 * 随机生成一种积木
	 */
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
	
	/**
	 * 正方形积木初始化
	 */
	public void init1(){
		isTransfrom = false;				
		for(int x=Global.WIDTH/2;x < Global.WIDTH/2+2 ; x++){
			body.add(new Point(x,-2));
			body.add(new Point(x,-3));			
		}
	}
	
	/**
	 *长条形积木初始化  
	 */
	public void init2(){
		 for(int x= Global.WIDTH/2-1 ; x < Global.WIDTH/2 + 3 ; x++){
			 body.add(new Point(x,-2));
		 }
	 }
	 
	/**
	 *T字形积木初始化  
	 */ 
	public void init3(){
		 for(int x = Global.WIDTH/2 - 1; x< Global.WIDTH/2 +2 ; x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2,-2));			 
	 }
	 
	/**
	 *右L形积木初始化  
	 */  
	public void init4(){//
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 +1,-2));
	 }
	 
	/**
	 *左L形积木初始化  
	 */  
	public void init5(){//
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 -1,-2));
	 }
	 
	/**
	 *左Z形积木初始化  
	 */  
	public void init6(){//
		 body.add(new Point(Global.WIDTH/2-1,-3));
		body.add(new Point(Global.WIDTH/2,-3));
		body.add(new Point(Global.WIDTH/2 ,-2));
		body.add(new Point(Global.WIDTH/2 + 1 ,-2));
	 }
	 
	/**
	 *右Z形积木初始化  
	 */   
	public void init7(){//
		 body.add(new Point(Global.WIDTH/2 + 1,-3));
		 body.add(new Point(Global.WIDTH/2 ,-3));
		 body.add(new Point(Global.WIDTH/2 -1 ,-2));
		 body.add(new Point(Global.WIDTH/2 ,-2));
	 }
	
	/**
	 * 积木下落，并生成积木位置改变事件
	 */
	public void move(){
//		System.out.println("block move ");
		for(Point p : body){
			p.y++;
		}		
		positionChanged();
	}
		
	/**
	 * 旋转积木
	 */
	public void changePositionUP() {
		ArrayList<Point> body_bk = new ArrayList<Point>();
		for(Point p: body){				
			body_bk.add(new Point(p.x,p.y));
		}
		Point o = body_bk.get(1);
		boolean isBottomOrWall = false;
		for(Point p: body_bk){
			int distance_y = o.x - p.x;
			int distance_x = o.y - p.y;
			int x = o.x + distance_x;
			int y = o.y - distance_y;
			if(controller.isBottomOrWall(x,y)){//判断是否可以旋转
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
	
	/**
	 * 右移积木
	 */
	public void changePositionR(){
		System.out.println("change positionR");		
		for(Point p : body){
			p.x++;
		}			
		positionChanged();			
	}
	
	/**
	 * 左移积木
	 */
	public void changePositionL(){
		System.out.println("change positionL");
		for(Point p : body){
			p.x--;
		}
		positionChanged();
	}
	
	/**
	 * 下移积木
	 */
	public void speedUp(){
		 System.out.println("speed up");
		 for(Point p : body){
			p.y++;
		 }
		 positionChanged();
	 }
	
	/**
	 * 积木位置发生变化，去检查积木是否触及底部
	 */
	public void positionChanged(){
		controller.isBlockHitBottom();
//		System.out.println("Positionchanged");
	}
	
	/**
	 * 在画布上画出自己
	 * @param g
	 */
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
//		System.out.println("drawMe blocks");
		g.setColor(Color.blue);
		for(Point p : body){
			g.fill3DRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE ,Global.CELL_SIZE,Global.CELL_SIZE, true);
		}
	}	
	
	
	private class BlocksDrive implements Runnable{//积木持续下落
		@Override
		public void run() {
			// TODO Auto-generated method stub
		 	while(canBlocksDrive){
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
	
	/**
	 *开始 积木持续下落
	 */
	public void start(){
		new Thread(new BlocksDrive()).start();
	}	

	/**
	 * 添加监听器
	 * @param controller
	 */
	public void addController(Controller controller){
		this.controller = controller;
	}
}
