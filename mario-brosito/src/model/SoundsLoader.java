package model;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundsLoader {
	
	/**
	 * <b>Description:</b>
	 * This function loads each individual sound in the specified route.
	 * @param ruta The path of the file.
	 * @return The clip that was loaded.
	 */
	public Clip load(String ruta) {
		Clip clip = null;
		try {
			InputStream is = ClassLoader.class.getResourceAsStream(ruta);
			AudioInputStream ais= AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return clip;
	}
	
	 /**
	  * <b>Description:</b>
	  * This function loads all the game sounds depending on the parameter.
	 * @param n The sound to be loaded.
	 * @return The clip that was created with the sound.
	 */

	public Clip loadSounds(int n) {
	    	Clip bang= null;
	    	if(n==0) {
	    		bang = load("/uilmusic/Ground_Theme.wav");
	    	}else if(n==1) {
	    		bang = load("/uilmusic/smb_1-up.wav");
	    	}else if(n==2) {
	    		bang = load("/uilmusic/smb_breakblock.wav");
	    	}else if(n==3) {
	    		bang = load("/uilmusic/smb_coin.wav");
	    	}else if(n==4) {
	    		bang = load("/uilmusic/smb_fireball.wav");
	    	}else if(n==5) {
	    		bang = load("/uilmusic/smb_flagpole.wav");
	    	}else if(n==6) {
	    		bang = load("/uilmusic/smb_jump-small.wav");
	    	}else if(n==7) {
	    		bang = load("/uilmusic/smb_jump-super.wav");
	    	}else if(n==8) {
	    		bang = load("/uilmusic/smb_kick.wav");
	    	}else if(n==9) {
	    		bang = load("/uilmusic/smb_mariodie.wav");
	    	}else if(n==10) {
	    		bang = load("/uilmusic/smb_pause.wav");
	    	}else if(n==11) {
	    		bang = load("/uilmusic/smb_kick.wav");
	    	}else if(n==12) {
	    		bang = load("/uilmusic/smb_powerup.wav");
	    	}else if(n==13) {
	    		bang = load("/uilmusic/smb_powerup_appears.wav");
	    	}else if(n==14) {
	    		bang = load("/uilmusic/smb_stage_clear.wav");
	    	}else if(n==15) {
	    		bang = load("/uilmusic/smb_vine.wav");
	    	}else if(n==16) {
	    		bang = load("/uilmusic/smb_warning.wav");
	    	}else if(n==17) {
	    		bang = load("/uilmusic/smb_world_clear.wav");
	    	}else if(n==18) {
	    		bang = load("/uilmusic/Star.wav");
	    	}else if(n==19) {
	    		bang = load("/uilmusic/smb_bowserfalls.wav");
	    	}else if(n==20) {
	    		bang = load("/uilmusic/smb_bowserfire.wav");
	    	}else if(n==21) {
	    		bang = load("/uilmusic/smb_bump.wav");
	    	}else if(n==22) {
	    		bang = load("/uilmusic/smb_gameover.wav");
	    	}else if(n==23) {
	    		bang = load("/uilmusic/UnderGround.wav");
	    	}else if(n==24) {
	    		bang = load("/uilmusic/Castle.wav");
	    	}else if(n==25) {
	    		bang = load("/uilmusic/Pause.wav");
	    	}
	    	return bang;
	    }
}
