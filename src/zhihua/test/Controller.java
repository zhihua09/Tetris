package zhihua.test;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class Controller extends KeyAdapter  {
	private Blocks blocks;
	private Panel panel;
	private JFrame frame;
	private Bottom bottom;
	private boolean ishit = false;//��ľ�Ƿ񴥼��ײ�
	private boolean canBlocksMoveByKeyPressd = true;//��ľ�Ƿ�����ð����ƶ�
		
	public Controller(JFrame frame,Panel panel) {
		this.panel = panel;
		this.frame = frame;
	}
	
	public void gameStart() {
		// TODO Auto-generated method stub
		bottom = new Bottom();
		panel.addBottom(bottom);
		newBlocks();
		panel.refresh();		
	}
	
	/**
	 * �����¼��Ĵ�����
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){		
		case KeyEvent.VK_UP:	//�����ϼ�ʹ��ľ��ת			
			if(blocks.isTransfrom)
				blocks.changePositionUP();				
			break;
		case KeyEvent.VK_DOWN://���¼�ʹ��ľ����һ��
			blocks.speedUp();
			break;
		case KeyEvent.VK_LEFT://�������ʹ��ľ����һ��
			canBlocksMoveByKeyPressd = true;
			for(Point p : blocks.body){
				if(p.x >= 0  && p.y >= 0){						
					if(p.x <= 0 || p.x > 0 && bottom.arr[p.x-1][p.y] ==1){
						canBlocksMoveByKeyPressd = false;
						break;
					}
				}
			}
			if(canBlocksMoveByKeyPressd)
				blocks.changePositionL();
			break;
		case KeyEvent.VK_RIGHT://�����Ƽ�ʹ��ľ����һ��
			canBlocksMoveByKeyPressd =true;
			for(Point p : blocks.body){
				if(p.x >= 0 && p.y >= 0){
					if(p.x >= Global.WIDTH || p.x < Global.WIDTH && bottom.arr[p.x+1][p.y] ==1){
						canBlocksMoveByKeyPressd = false;
						break;
					}						
				}
			}
			if(canBlocksMoveByKeyPressd)
				blocks.changePositionR();
			break;
		}	
	}
	
	/**
	 * ����һ���»�ľ
	 */
	public void newBlocks(){		
		frame.addKeyListener(this);
		blocks = new Blocks();	
		System.out.println("new blocks");
		blocks.addController(this);
		panel.addBlocks(blocks);
		blocks.start();		
	 }
	 
	/**
	 * ��ľ�Ƿ񴥼��ײ�
	 */
	public void isBlockHitBottom() {
		// TODO Auto-generated method stub
		ishit = false;
		for(Point p : blocks.body){
			if(p.y >= 0 && p.x >=0){				
				if(p.y >=Global.HEIGTH || bottom.arr[p.x][p.y+1] == 1){//��ľ�����ײ�
				ishit = true;
				break;
				}
			}
		}
		if(ishit){
			blocks.canBlocksDrive = false;//��ľֹͣ����
			frame.removeKeyListener(this);//�����޷��ƶ���ľ
			bottom.hitBottom(blocks);
			blocks.body.removeAll(blocks.body);//��ľ�ӻ�������ʧ
			bottom.isInLine();
			if(!bottom.isBottomHitTop()){	//����Ϸδ����������µĻ�ľ			
				newBlocks();
			}
		}
//		System.out.println("is blocks hit bottom ?");
	}
	
	/**
	 * �жϵ�ǰ�ĵ��Ƿ�Ϊǽ��ײ�
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isBottomOrWall(int x, int y) {
		// TODO Auto-generated method stub
		if( x<0 || x> Global.WIDTH || y > Global.HEIGTH )
			return true;
		if(y>=0 && bottom.arr[x][y] == 1)
			return true;
		return false;	
	}

}
