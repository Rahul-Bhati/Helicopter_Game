import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;

public class heli extends Applet implements MouseListener{
	int x,y,w,h,s=0;
	int x1,y1,flag=0,start=0,flagy,flagx;
	AudioClip au,over;
	public void init(){
		x = 0 ;
		x1 = 50 ;
		y1 = 540 ;
		flagy = 0;
		au=getAudioClip(getCodeBase(),"hfly.wav");
		over=getAudioClip(getCodeBase(),"hover.wav");
		addMouseListener(this);
	}
	public void mouseClicked(MouseEvent e){
		if(start==1){
			y1 = y1 - 20 ;
			au.play();
		}
	}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){
		start = 1 ;
		flagy = 1 ;
		au.loop();
		repaint() ;
	}
	public void mouseExited(MouseEvent e){
		start = 0 ;
		au.stop();
		repaint();
	}
	public void paint(Graphics g){
		Font obj= new Font("Game Over", Font.BOLD, 30);
		Image img = getImage(getCodeBase(),"field.jpg");
		g.drawImage(img,0,0,1080,1080,this);
		Image img1 = getImage(getCodeBase(),"1.jpg");
		g.drawImage(img1,x,y,w,h,this);
		Image helicopter = getImage(getCodeBase(),"helicopter.gif");
		g.drawImage(helicopter,x1,y1,150,100,this);	
		g.drawString("score"+s,540,25);
	
		if(start==1 && flag==0){
				s++;
				if(x==1080){
					flagx = 0 ;
				}
				if(x==0){
					Random r = new Random();
					y = r.nextInt(500) ;
					Random r1 = new Random();
					w = r1.nextInt(250)+5 ;
					Random r2 = new Random();
				 	h = r2.nextInt(400)+100 ;
					flagx = 1 ;
				}
				if(flagx==0){
					x = x - 4 ;
				}
				if(flagx==1){
					x = x + 1080 ;
				}
				if(y1==0 || y1==1080 || (x<=150 &&  y1<=y+h && y1+100>=y )){
					flag = 1 ;
				}
				
				try{
					Thread.sleep(10);
				}
				catch(InterruptedException e){}
				
				y1 = y1 + 2 ;
				repaint();
		}
		else if(flag == 1){
			start = 0;
			over.play();
			Image gameOver = getImage(getCodeBase(),"gameOver.jpg");
			g.drawImage(gameOver,290,290,this);
		}
		else if(start==0 && flag==0 && flagy==1){
			Image pause = getImage(getCodeBase(),"pauseGame.jpeg");
			g.drawImage(pause,290,290,this);
			over.stop();
		}
		else if(start==0 && flagy==0){
			Image gameStart = getImage(getCodeBase(),"gameStart.jpg");
			g.drawImage(gameStart,0,0,this);
		}

	}	
}
//<applet code="heli.class" width=1080 height=1080></applet>


