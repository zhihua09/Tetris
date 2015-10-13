package zhihua.test;

import java.awt.event.KeyAdapter;

public class Controller extends KeyAdapter implements BlockListenerInterface {
	private Blocks blocks;
	private Panel panel;
		
	public Controller(Blocks blocks, Panel panel) {
		super();
		this.blocks = blocks;
		this.panel = panel;
	}


	public void blockMoved(){
		panel.display(blocks);
	}

	
}
