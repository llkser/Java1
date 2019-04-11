import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Kang Liu
 */
public class Track{
	private ArrayList<Point> tr;
	public Track() {
		tr=new ArrayList<Point>();
	}
	public void readFile(String str) {
		try {
			Scanner scanner= new Scanner(new File(str));
		} catch (FileNotFoundException e) {
			
		}
	}
	public void add(Point a) {
		tr.add(a);
	}
	public int size() {
		return tr.size();
	}
	public Point get(int index) {
		Point a;
		if(index<0||index>=this.size())
			throw new GPSException("Index Out of Range");
		else {
			a=tr.get(index);
		}
		return a;
	}
}