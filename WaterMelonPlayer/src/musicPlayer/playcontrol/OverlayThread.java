package musicPlayer.playcontrol;

import java.awt.Color;

import musicPlayer.form.PlayerFormSettings;

public class OverlayThread implements Runnable{
	public int Rcol = 31, Gcol = 29, Bcol = 62, Rtemp = 0, Gtemp = 0, Btemp = 0;
	
	PlayerFormSettings formComponents;
	public boolean stop;
	
	public void setStop(boolean stop){
		this.stop = stop;
	}

	public void setInitColor(int Rcol,int  Gcol,int  Bcol,int  Rtemp,int  Gtemp,int  Btemp) {
		this.Rcol = Rcol;
		this.Gcol = Gcol;
		this.Bcol = Bcol;
		this.Rtemp = Rtemp;
		this.Gtemp = Gtemp;
		this.Btemp = Btemp;
		
	}
	public OverlayThread(PlayerFormSettings formComponents) {
		// TODO Auto-generated constructor stub
		this.formComponents = formComponents;
	}
	
	@Override
	public void run() {	
			System.out.println("쓰레드 생성");
			while (!stop) {
				
				double randomValue = Math.random();
				int ranCheck = (int)(randomValue*3)+1;
				
				if(Rcol >= 254 && Rtemp == 0)
					Rtemp = 1;
				if(Gcol >= 254 && Gtemp == 0)
					Gtemp = 1;
				if(Bcol >= 254 && Btemp == 0)
					Btemp = 1;
				
				if(Rcol <= 31 && Rtemp == 1)
					Rtemp = 0;
				if(Gcol <= 29 && Gtemp == 1)
					Gtemp = 0;
				if(Bcol <= 62 && Btemp == 1)
					Btemp = 0;
				
				if(ranCheck == 1 && Rtemp == 0)
					Rcol += 1;
				if(ranCheck == 2 && Gtemp == 0)
					Gcol += 1;
				if(ranCheck == 3 && Btemp == 0)
					Bcol += 1;
				
				if(ranCheck == 1 && Rtemp == 1)
					Rcol -= 1;
				if(ranCheck == 2 && Gtemp == 1)
					Gcol -= 1;
				if(ranCheck == 3 && Btemp == 1)
					Bcol -= 1;
				
			/*
			 * System.out.println(ranCheck); System.out.println(Rcol);
			 * System.out.println(Gcol); System.out.println(Bcol); System.out.println(stop);
			 */

				
				
				formComponents.setBackground(new Color(Rcol, Gcol, Bcol));
				formComponents.MusicList.setBackground(new Color(Rcol, Gcol, Bcol));
				formComponents.MusicItems.setBackground(new Color(Rcol-10, Gcol-10, Bcol-10));
				formComponents.MusicListScroll.setBackground(new Color(Rcol, Gcol, Bcol));
				formComponents.MusicListScroll.getViewport().setBackground(new Color(Rcol, Gcol, Bcol));
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//Thread.currentThread().interrupt();
					e.printStackTrace();
				}
			}
			
			System.out.println("쓰레드 종료");
	}
}