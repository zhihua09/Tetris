package zhihua.test;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter implements BlockListenerInterface {
	private Blocks blocks;
	private Panel panel;
		
	public Controller(Blocks blocks, Panel panel) {
		super();
		this.blocks = blocks;
		this.panel = panel;
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){		
		case KeyEvent.VK_DOWN:
			blocks.speedDown();
			break;
		}
	}

	@Override
	public void blockMoved() {
		// TODO Auto-generated method stub
		panel.refresh(blocks);
	}
}
