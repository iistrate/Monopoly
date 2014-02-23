import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class World {
	private static ArrayList<Tile> tilelist = new ArrayList<Tile>();
	
	
	public World(){
		System.out.println("Ioans a monkey");
	}

	public void addtile(int w, int h, int x, int y, int sX, int sY){
		tilelist.add(new Tile(w, h, x, y, sX, sY));
	}
	
	public static ArrayList<Tile> getTilelist() {
		return tilelist;
	}

	public static void setTilelist(ArrayList<Tile> tilelist) {
		World.tilelist = tilelist;
	}
	
	// Non-Database load and save, To: Ioan From: Jay    :P  HAH-HAH
	@SuppressWarnings("unchecked")
	public void load() throws IOException, ClassNotFoundException{
        FileInputStream prein = new FileInputStream("tilelist.dat");
        ObjectInputStream in = new ObjectInputStream(prein);
        tilelist = (ArrayList<Tile>)in.readObject();
        in.close();
    }
	
	public void save() throws IOException  {
	    FileOutputStream fos = new FileOutputStream("tilelist.dat");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);   
	    try {
			oos.writeObject(tilelist);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    oos.close(); 
	} 

}