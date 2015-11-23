package zhihua.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JFrame;

public class GameStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bottom bottom = new Bottom();
		Panel panel = new Panel();		
		JFrame frame = new JFrame("¶íÂÞË¹·½¿é");
		Controller controller = new Controller(panel,bottom,frame);
		
		frame.setSize((Global.WIDTH+1)*Global.CELL_SIZE+15, (Global.HEIGTH+1)*Global.CELL_SIZE+35);
		frame.setLocation(540, 50);
		panel.setSize((Global.WIDTH+1)*Global.CELL_SIZE,(Global.HEIGTH+1)*Global.CELL_SIZE);
		frame.add(panel,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);	
		
				
		frame.addKeyListener(controller);
		panel.addKeyListener(controller);
		
		controller.newBlocks();
		panel.refresh();		

		System.out.println("end");
	}

}
