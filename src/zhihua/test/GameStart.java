package zhihua.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Blocks blocks = new Blocks();
		Panel panel = new Panel();
		Controller controller = new Controller(blocks,panel);
		
		JFrame frame = new JFrame("¶íÂÞË¹·½¿é");
		frame.setSize(11*25+15, 21*25+35);
		panel.setSize(11*25,21*25);
		frame.add(panel,BorderLayout.CENTER);
		panel.display(blocks);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		frame.addKeyListener(controller);
		panel.addKeyListener(controller);
		blocks.addBlockListener(controller);
		
		blocks.start();
		
//		for (int i = 0; i <22; i++) {
//			blocks.move();
//			controller.blockMoved();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}
		System.out.println("end");
	}

}
