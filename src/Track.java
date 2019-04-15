import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
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
	public void readFile(String str) throws FileNotFoundException{
			if(this.size()>0)
				tr.clear();
			Scanner sc= new Scanner(new File(str));
			Scanner vScanner= null;
			sc.nextLine();
			int index;
			String s;
			double a,b,c;
			ZonedDateTime data=null;
			a=b=c=0;
			Point p;
			while(sc.hasNext())
			{
				vScanner=new Scanner(sc.nextLine());
				vScanner.useDelimiter(",");
				index=0;
				while(vScanner.hasNext())
				{
					s=vScanner.next();
					if(index==0)
						data=ZonedDateTime.parse(s);
					else if(index==1)
						a=Double.parseDouble(s);
					else if(index==2)
						b=Double.parseDouble(s);
					else if(index==3)
					{
						c=Double.parseDouble(s);
						p=new Point(data, a, b, c);
						tr.add(p);
					}
					index++;
					if(index==4)
						index=0;
				}
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