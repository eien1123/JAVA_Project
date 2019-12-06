package Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music implements Runnable {

	private Player player;
	private boolean loop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean loop) {
		try {
			this.loop = loop;
			file = new File("./sound/" + name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		if(player == null) {
			return 0;
		}
		return player.getPosition();
	}
	
	public void close() {
		loop = false;
		player.close();
	}
	
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(loop);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}	
	}
}
