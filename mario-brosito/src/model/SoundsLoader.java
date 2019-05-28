package model;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundsLoader {
	
	public static Clip cargarSonido(final String ruta) {
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
	 public static Clip loadSounds(int n) {
	    	Clip bang= null;
	    	if(n==0) {
	    		bang = cargarSonido("/uilmusic/Ground_Theme.wav");
	    	}else if(n==1) {
	    		bang = cargarSonido("/uilmusic/smb_1-up.wav");
	    	}else if(n==2) {
	    		bang = cargarSonido("/uilmusic/smb_breakblock.wav");
	    	}else if(n==3) {
	    		bang = cargarSonido("/uilmusic/smb_coin.wav");
	    	}else if(n==4) {
	    		bang = cargarSonido("/uilmusic/smb_fireball.wav");
	    	}else if(n==5) {
	    		bang = cargarSonido("/uilmusic/smb_flagpole.wav");
	    	}else if(n==6) {
	    		bang = cargarSonido("/uilmusic/smb_jump-small.wav");
	    	}else if(n==7) {
	    		bang = cargarSonido("/uilmusic/smb_jump-super.wav");
	    	}else if(n==8) {
	    		bang = cargarSonido("/uilmusic/smb_kick.wav");
	    	}else if(n==9) {
	    		bang = cargarSonido("/uilmusic/smb_mariodie.wav");
	    	}else if(n==10) {
	    		bang = cargarSonido("/uilmusic/smb_pause.wav");
	    	}else if(n==11) {
	    		bang = cargarSonido("/uilmusic/smb_kick.wav");
	    	}else if(n==12) {
	    		bang = cargarSonido("/uilmusic/smb_powerup.wav");
	    	}else if(n==13) {
	    		bang = cargarSonido("/uilmusic/smb_powerup_appears.wav");
	    	}else if(n==14) {
	    		bang = cargarSonido("/uilmusic/smb_stage_clear.wav");
	    	}else if(n==15) {
	    		bang = cargarSonido("/uilmusic/smb_vine.wav");
	    	}else if(n==16) {
	    		bang = cargarSonido("/uilmusic/smb_warning.wav");
	    	}else if(n==17) {
	    		bang = cargarSonido("/uilmusic/smb_world_clear.wav");
	    	}else if(n==18) {
	    		bang = cargarSonido("/uilmusic/Star.wav");
	    	}else if(n==19) {
	    		bang = cargarSonido("/uilmusic/smb_bowserfalls.wav");
	    	}else if(n==20) {
	    		bang = cargarSonido("/uilmusic/smb_bowserfire.wav");
	    	}else if(n==21) {
	    		bang = cargarSonido("/uilmusic/smb_bump.wav");
	    	}else if(n==22) {
	    		bang = cargarSonido("/uilmusic/smb_gameover.wav");
	    	}else if(n==23) {
	    		bang = cargarSonido("/uilmusic/UnderGround.wav");
	    	}else if(n==24) {
	    		bang = cargarSonido("/uilmusic/Castle.wav");
	    	}
	    	return bang;
	    }
}
