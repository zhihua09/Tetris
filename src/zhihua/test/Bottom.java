package zhihua.test;

import java.awt.Color;
import java.awt.Graphics;

public class Bottom {
    private int[][] arr = new int[11][21];
    private boolean flag= true;
    private boolean ishit = false;
    private Blocks blocks;
    private Controller controller;
    
    {
    	for(int x=0 ; x<10 ; x++){
    		arr[x][20] = 1;
    	}
    }
    public void isInLine(){
    	for(int y=20; y>= 0; y--,flag = true){    		
    		for(int x=0 ; x<11 ;x++){
    			if(arr[x][y]==0)
    				flag = false;    				
    		}    		
	    	if(flag==true){
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
    	for(int x=0 ; x<11 ; x++)
    		arr[x][y]= 0;
    	for(int a=y ;a>0 ;a--){
    		for(int x=0 ;x<11 ; x++){
    			arr[x][a] = arr[x][a-1];
    		}
    	}
    	for(int x=0;x<11;x++)
    		arr[x][0]=0;
    }
    
    public void isBlockHitBottom(){    	
    	System.out.println("isBlockHitBottom");    	
    	if(blocks.y == 20 || arr[blocks.x][blocks.y+1] ==1)
    	{
    		//blocks.bottom =this;
    		hitBottom(blocks);
    		blocks.a = false;    		
    		isInLine();    		
    		controller.newBlocks();
    		controller.blocksStart();
//    		blocks = new Blocks();
//    		blocks.addBottom(this);
//    		blocks.start();
//    		System.out.println("new block start---------------------------------");
    	}    	
    }
    
    public void hitBottom(Blocks blocks){
    	arr[blocks.x][blocks.y] = 1;
    }
    public void addBlocks(Blocks blocks){
    	this.blocks = blocks;
    }
    
    public void addController(Controller controller){
    	this.controller  = controller;
    }
    
    public void drawMe(Graphics g){
    	System.out.println("draw bottom");
    	g.setColor(Color.BLUE);
    	for(int y=20 ; y>0; y--){
    		for(int x=0 ; x<11 ;x++){
    			if(arr[x][y]==1)
    				g.fill3DRect(x*25, y*25, 25, 25, true);
    		}
    	}
    }
	
}
