package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Blocks{
	private Controller controller;
	public ArrayList<Point> body = new ArrayList<Point>();
	boolean canBlocksDrive = true;//��ľ�Ƿ�����
	final int MOVETIME =600;//�����ٶ�
	public boolean isTransfrom ;//��ľ�Ƿ������ת
	
	/**
	 * �������һ�ֻ�ľ
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
	 * �����λ�ľ��ʼ��
	 */
	public void init1(){
		isTransfrom = false;				
		for(int x=Global.WIDTH/2;x < Global.WIDTH/2+2 ; x++){
			body.add(new Point(x,-2));
			body.add(new Point(x,-3));			
		}
	}
	
	/**
	 *�����λ�ľ��ʼ��  
	 */
	public void init2(){
		 for(int x= Global.WIDTH/2-1 ; x < Global.WIDTH/2 + 3 ; x++){
			 body.add(new Point(x,-2));
		 }
	 }
	 
	/**
	 *T���λ�ľ��ʼ��  
	 */ 
	public void init3(){
		 for(int x = Global.WIDTH/2 - 1; x< Global.WIDTH/2 +2 ; x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2,-2));			 
	 }
	 
	/**
	 *��L�λ�ľ��ʼ��  
	 */  
	public void init4(){//
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 +1,-2));
	 }
	 
	/**
	 *��L�λ�ľ��ʼ��  
	 */  
	public void init5(){//
		 for(int x = Global.WIDTH/2 -1 ; x< Global.WIDTH/2 +2 ;x++){
			 body.add(new Point(x,-3));
		 }
		 body.add(new Point(Global.WIDTH/2 -1,-2));
	 }
	 
	/**
	 *��Z�λ�ľ��ʼ��  
	 */  
	public void init6(){//
		 body.add(new Point(Global.WIDTH/2-1,-3));
		body.add(new Point(Global.WIDTH/2,-3));
		body.add(new Point(Global.WIDTH/2 ,-2));
		body.add(new Point(Global.WIDTH/2 + 1 ,-2));
	 }
	 
	/**
	 *��Z�λ�ľ��ʼ��  
	 */   
	public void init7(){//
		 body.add(new Point(Global.WIDTH/2 + 1,-3));
		 body.add(new Point(Global.WIDTH/2 ,-3));
		 body.add(new Point(Global.WIDTH/2 -1 ,-2));
		 body.add(new Point(Global.WIDTH/2 ,-2));
	 }
	
	/**
	 * ��ľ���䣬�����ɻ�ľλ�øı��¼�
	 */
	public void move(){
//		System.out.println("block move ");
		for(Point p : body){
			p.y++;
		}		
		positionChanged();
	}
		
	/**
	 * ��ת��ľ
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
			if(controller.isBottomOrWall(x,y)){//�ж��Ƿ������ת
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
	 * ���ƻ�ľ
	 */
	public void changePositionR(){
		System.out.println("change positionR");		
		for(Point p : body){
			p.x++;
		}			
		positionChanged();			
	}
	
	/**
	 * ���ƻ�ľ
	 */
	public void changePositionL(){
		System.out.println("change positionL");
		for(Point p : body){
			p.x--;
		}
		positionChanged();
	}
	
	/**
	 * ���ƻ�ľ
	 */
	public void speedUp(){
		 System.out.println("speed up");
		 for(Point p : body){
			p.y++;
		 }
		 positionChanged();
	 }
	
	/**
	 * ��ľλ�÷����仯��ȥ����ľ�Ƿ񴥼��ײ�
	 */
	public void positionChanged(){
		controller.isBlockHitBottom();
//		System.out.println("Positionchanged");
	}
	
	/**
	 * �ڻ����ϻ����Լ�
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
	
	
	private class BlocksDrive implements Runnable{//��ľ��������
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
	 *��ʼ ��ľ��������
	 */
	public void start(){
		new Thread(new BlocksDrive()).start();
	}	

	/**
	 * ��Ӽ�����
	 * @param controller
	 */
	public void addController(Controller controller){
		this.controller = controller;
	}
}
