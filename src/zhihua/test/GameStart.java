package zhihua.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Panel panel = new Panel();		
		JFrame frame = new JFrame("¶íÂÞË¹·½¿é");
		Controller controller = new Controller(frame,panel);
		
		frame.setSize((Global.WIDTH+1)*Global.CELL_SIZE+15, (Global.HEIGTH+1)*Global.CELL_SIZE+35);
		frame.setLocation(540, 50);
		panel.setSize((Global.WIDTH+1)*Global.CELL_SIZE,(Global.HEIGTH+1)*Global.CELL_SIZE);
		frame.add(panel,BorderLayout.CENTER);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);	
				
		controller.gameStart();
	}

}
