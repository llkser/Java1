import java.io.FileNotFoundException;

public class TrackInfo {
	public static void main(String[] args) {
		if(args.length == 0){  
			System.out.println("No File to be Read!");  
			System.exit(-1);
	    } 
		Track track1=new Track();
		try {
			track1.readFile(args[1]);
		}
		catch (FileNotFoundException e) {
			e.getMessage();
		}
	}
}