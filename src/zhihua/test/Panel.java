package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {
	
	private Blocks blocks;
	
	public void display(Blocks blocks){
		this.blocks = blocks;		
		this.repaint();
		System.out.println("display panel");
	}
	
	protected  void paintComponent(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 11*25, 21*25);		
		
		if(blocks != null){
			this.blocks.drawMe(g);
		}
		System.out.println("paintComponent");
		
	}
	
	private class PanelDrive implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				display(blocks);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}				
	}
	
	public void refresh(Blocks blocks){
		new Thread(new PanelDrive()).start();
	}

}
