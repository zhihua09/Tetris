package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private Blocks blocks;
	private Bottom bottom;
	
	public void display(Blocks blocks,Bottom bottom){
		this.blocks = blocks;
		this.bottom = bottom;
		this.repaint();
		System.out.println("display panel");
	}
	
	protected  void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 11*25, 21*25);		
		
		if(blocks != null){
			this.blocks.drawMe(g);			
		}
		if(bottom != null){
			this.bottom.drawMe(g);
		}
		System.out.println("paintComponent");
		
	}
	
	private class PanelDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				display(blocks,bottom);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}				
	}
	
	public void refresh(){
		new Thread(new PanelDrive()).start();
	}
	
	public void addBlocks(Blocks blocks){
		this.blocks = blocks;
	}

}
