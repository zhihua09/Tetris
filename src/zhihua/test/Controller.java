package zhihua.test;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private JFrame frame;
	private Bottom bottom;
	private boolean ishit = false;//积木是否触及底部
	private boolean canBlocksMoveByKeyPressd = true;//积木是否可以用按键移动
		
	public Controller(JFrame frame,Panel panel) {
		this.panel = panel;
		this.frame = frame;
	}
	
	public void gameStart() {
		// TODO Auto-generated method stub
		bottom = new Bottom();
		panel.addBottom(bottom);
		newBlocks();
		panel.refresh();		
	}
	
	/**
	 * 按键事件的处理方法
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){		
		case KeyEvent.VK_UP:	//按向上键使积木旋转			
			if(blocks.isTransfrom)
				blocks.changePositionUP();				
			break;
		case KeyEvent.VK_DOWN://按下键使积木下移一格
			blocks.speedUp();
			break;
		case KeyEvent.VK_LEFT://按向左键使积木左移一格
			canBlocksMoveByKeyPressd = true;
			for(Point p : blocks.body){
				if(p.x >= 0  && p.y >= 0){						
					if(p.x <= 0 || p.x > 0 && bottom.arr[p.x-1][p.y] ==1){
						canBlocksMoveByKeyPressd = false;
						break;
					}
				}
			}
			if(canBlocksMoveByKeyPressd)
				blocks.changePositionL();
			break;
		case KeyEvent.VK_RIGHT://按右移键使积木右移一格
			canBlocksMoveByKeyPressd =true;
			for(Point p : blocks.body){
				if(p.x >= 0 && p.y >= 0){
					if(p.x >= Global.WIDTH || p.x < Global.WIDTH && bottom.arr[p.x+1][p.y] ==1){
						canBlocksMoveByKeyPressd = false;
						break;
					}						
				}
			}
			if(canBlocksMoveByKeyPressd)
				blocks.changePositionR();
			break;
		}	
	}
	
	/**
	 * 生成一个新积木
	 */
	public void newBlocks(){		
		frame.addKeyListener(this);
		blocks = new Blocks();	
		System.out.println("new blocks");
		blocks.addController(this);
		panel.addBlocks(blocks);
		blocks.start();		
	 }
	 
	/**
	 * 积木是否触及底部
	 */
	public void isBlockHitBottom() {
		// TODO Auto-generated method stub
		ishit = false;
		for(Point p : blocks.body){
			if(p.y >= 0 && p.x >=0){				
				if(p.y >=Global.HEIGTH || bottom.arr[p.x][p.y+1] == 1){//积木触及底部
				ishit = true;
				break;
				}
			}
		}
		if(ishit){
			blocks.canBlocksDrive = false;//积木停止下落
			frame.removeKeyListener(this);//按键无法移动积木
			bottom.hitBottom(blocks);
			blocks.body.removeAll(blocks.body);//积木从画布上消失
			bottom.isInLine();
			if(!bottom.isBottomHitTop()){	//若游戏未结束则出现新的积木			
				newBlocks();
			}
		}
//		System.out.println("is blocks hit bottom ?");
	}
	
	/**
	 * 判断当前的点是否为墙或底部
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isBottomOrWall(int x, int y) {
		// TODO Auto-generated method stub
		if( x<0 || x> Global.WIDTH || y > Global.HEIGTH )
			return true;
		if(y>=0 && bottom.arr[x][y] == 1)
			return true;
		return false;	
	}

}
