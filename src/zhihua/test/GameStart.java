package zhihua.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bottom bottom = new Bottom();
		Panel panel = new Panel();
		Controller controller = new Controller(panel,bottom);
		
		JFrame frame = new JFrame("¶íÂÞË¹·½¿é");
		frame.setSize((Global.WIDTH+1)*Global.CELL_SIZE+15, (Global.HEIGTH+1)*Global.CELL_SIZE+35);
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
