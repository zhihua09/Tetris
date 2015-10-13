package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

public class Blocks{
	int x = 5;
	int y =-1;
	
	public void move(){
		System.out.println("block move ");
		y++;
				
	}
		
	public void changePosition(){
		System.out.println("change position");
		
	}
	
	public void speedUp(){
		System.out.println("speed up");
	}
	
	public void drawMe(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("drawMe");
		g.setColor(Color.blue);
		g.fill3DRect(x*25, y*25 ,25,25, true);
	}
	
	
}
