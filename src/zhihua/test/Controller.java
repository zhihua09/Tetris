package zhihua.test;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private Bottom bottom;
	private boolean ishit = false;
	private boolean isgameover = false;
	private boolean b;
		
	public Controller(Panel panel) {
		this.panel = panel;
		this.bottom = new Bottom();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){		
		case KeyEvent.VK_UP:				
			if(blocks.isTransfrom)
				blocks.changePositionUP();				
			break;
		case KeyEvent.VK_LEFT:
			b =true;
			for(Point p : blocks.body){
				if(p.x > 0 && p.y >= 0){						
					if(p.x > 0 && bottom.arr[p.x-1][p.y] ==1){
						b = false;
						break;
					}
				}
			}
			if(b)
				blocks.changePositionL();
			break;
		case KeyEvent.VK_RIGHT:
			b =true;
			for(Point p : blocks.body){
				if(p.y >= 0){
					if(p.x < Global.WIDTH && bottom.arr[p.x+1][p.y] ==1){
						b = false;
						break;
					}						
				}
			}
			if(b)
				blocks.changePositionR();
			break;
		case KeyEvent.VK_DOWN:
			blocks.speedUp();
			break;
		}	
	}
	
	 public void newBlocks(){		
		blocks = new Blocks();	
		System.out.println("new blocks");
		blocks.addController(this);
		panel.addBlocks(blocks);
		panel.addBottom(bottom);
		blocks.start();		
	 }
	 

	public void isBlockHitBottom() {
		// TODO Auto-generated method stub
		ishit = false;
		for(Point p : blocks.body){
			if(p.y >= 0 && p.x >=0){				
				if(p.y >=Global.HEIGTH || bottom.arr[p.x][p.y+1] == 1){
				blocks.isGameOver = true;
				ishit = true;
				break;
				}
			}
		}
		if(ishit){
			bottom.hitBottom(blocks);
			bottom.isInLine();
			for(Point p:blocks.body){
				if(p.y==0){
					isgameover = true;	
					break;
				}			
			}
			blocks.body.removeAll(blocks.body);
			if(isgameover == false){				
				newBlocks();
			}
		}
//		System.out.println("is blocks hit bottom ?");
	}
	
	public boolean isBottomOrWall(int x, int y) {
		// TODO Auto-generated method stub
		if( x<0 || x> Global.WIDTH || y > Global.HEIGTH )
			return true;
		if(y>=0 && bottom.arr[x][y] == 1)
			return true;
		return false;	
	}
}
