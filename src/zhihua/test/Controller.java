package zhihua.test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private Bottom bottom;
	
	public Controller( Panel panel, Bottom bottom) {
		super();		
		this.panel = panel;
		this.bottom = bottom;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			blocks.changePositionUP();
			break;
		case KeyEvent.VK_LEFT:
			blocks.changePositionL();
			break;
		case KeyEvent.VK_RIGHT:
			blocks.changePositionR();
			break;
		case KeyEvent.VK_DOWN:
			blocks.speedUp();
			break;
		}
	}
	 public void newBlocks(){
		
		this.blocks = new Blocks();
		bottom.addBlocks(blocks);
		panel.addBlocks(blocks);		
		blocks.addBottom(bottom);
		blocks.start();
		
	 }
	 
	 public void blocksStart(){		
		 panel.display(blocks,bottom);
		 
	 }
}
