package zhihua.test;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private Bottom bottom;
	private boolean flag = true;
	private boolean ishit = false;
	private boolean isgameover = false;
	private boolean b;
		
	public Controller(Panel panel, Bottom bottom) {
		super();
		this.panel = panel;
		this.bottom = bottom;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(flag == true){
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
	}
	
	 public void newBlocks(){		
		blocks = new Blocks();		
		blocks.addController(this);
		panel.addBlocks(blocks);
		panel.addBottom(bottom);
		blocks.start();		
	 }
	 
//	 public void blocksStart(){		
//		 panel.display(blocks,bottom);
//		 
//	 }
	public void isBlockHitBottom() {
		// TODO Auto-generated method stub
		ishit = false;
		for(Point p : blocks.body){
			if(p.y >= 0){				
				if(p.y ==20 || bottom.arr[p.x][p.y+1] == 1){
				blocks.a = false;
				ishit = true;
				break;
				}
			}
		}
		if(ishit){
			bottom.hitBottom(blocks);
			blocks.body.removeAll(blocks.body);
			bottom.isInLine();			
			for(Point p : blocks.body){
				if(p.y == 0){
					gameOver();
					isgameover = true;
				}					
			}
			if(isgameover == false){				
				newBlocks();
			}
		}
		System.out.println("is blocks hit bottom ?");
	}
	public void gameOver() {
		// TODO Auto-generated method stub
		flag = false;
		System.out.println("game over");
	}
	public boolean isBottomOrWall(int x, int y) {
		// TODO Auto-generated method stub
		if( x<0 || x>11 || y > 20 )
			return true;
		if(y>=0 && bottom.arr[x][y] == 1)
			return true;
		return false;
	}
}
