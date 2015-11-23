package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Bottom {
//	private Controller controller;
    public int[][] arr = new int[Global.WIDTH+1][Global.HEIGTH+1];
    private boolean inLine= true;

    public void hitBottom(Blocks blocks){
    	for(Point p : blocks.body){    		
    		if(p.y>=0)
    			arr[p.x][p.y] = 1;
    	}
    }
    
    public void isInLine(){
    	for(int y= 0; y < Global.HEIGTH+1; y++,inLine = true){    		
    		for(int x=0 ; x< Global.WIDTH+1 ;x++){
    			if(arr[x][y]==0){
    				inLine = false;
    				break;    				
    			}
    		}    		
	    	if(inLine==true){
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		lineVanish(y);	
	    	}
    	}    	
    }
    
    public void lineVanish(int y){
    	for(int x=0 ; x<Global.WIDTH+1 ; x++)
    		arr[x][y]= 0;
    	for(int a=y ;a>0 ;a--){
    		for(int x=0 ;x<Global.WIDTH+1 ; x++){
    			arr[x][a] = arr[x][a-1];
    		}
    	}
    	for(int x=0;x< Global.WIDTH+1;x++)
    		arr[x][0]=0;
    }         
  
//    public void addController(Controller controller){
//    	this.controller  = controller;
//    }
    
    public void drawMe(Graphics g){
//    	System.out.println("draw bottom");
    	g.setColor(Color.BLUE);
    	for(int y=Global.HEIGTH ; y>=0; y--){
    		for(int x=0 ; x<Global.WIDTH+1 ;x++){
    			if(arr[x][y]==1)
    				g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
    		}
    	}
    }
	
}
