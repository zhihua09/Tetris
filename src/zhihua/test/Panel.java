package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel{
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

}
