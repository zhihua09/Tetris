package zhihua.test;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private Bottom bottom;
	private JFrame frame;
	private boolean flag = true;
	private boolean ishit = false;
	private boolean isgameover = false;
	private boolean b;
		
	public Controller(Panel panel, Bottom bottom,JFrame frame) {
		super();
		this.panel = panel;
		this.bottom = bottom;
		this.frame = frame;
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
	
	 private void reStart() {
		// TODO Auto-generated method stub
		
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
			if(p.y >= 0 && p.x >=0){				
				if(p.y >=Global.HEIGTH || bottom.arr[p.x][p.y+1] == 1){
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
			if(bottom.arr[Global.WIDTH/2][10]==1){
				gameOver();
				isgameover = true;				
			}			
			if(isgameover == false){				
				newBlocks();
			}
		}
		System.out.println("is blocks hit bottom ?");
	}
	public void gameOver() {
		// TODO Auto-generated method stub
		newDialog();
		System.out.println("game over");
	}
	private void newDialog() {
		// TODO Auto-generated method stub
		Dialog d = new Dialog(frame,"¶íÂÞË¹·½¿é-Game Over",true);
		Button b1 = new Button("(Q)uit");
		Button b2 = new Button("(R)eStart");		
		d.add(b1);
		d.add(b2);
		d.setSize(400, 100);
		d.setLocation(500, 300);
		d.setLayout(new FlowLayout());
		d.setVisible(true);
		d.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e1) {
				// TODO Auto-generated method stub
				switch(e1.getKeyCode()){
				case KeyEvent.VK_Q:
					System.exit(0);
					break;
				case KeyEvent.VK_R:
					reStart();
					break;
				}
			}			
		});
	
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
