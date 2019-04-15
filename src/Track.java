import java.io.File;
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a track that can store some points and compute some kinds of statistical data.
 *
 * @author Kang Liu
 */
public class Track{
	private ArrayList<Point> tr;
	public Track() {
		tr=new ArrayList<Point>();
	}
	public void readFile(String str) throws FileNotFoundException,GPSException{
			if(this.size()>0)
				tr.clear();
			Scanner sc= new Scanner(new File(str));
			Scanner vScanner= null;
			sc.nextLine();
			int index=0;
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
						try {
							p=new Point(data, a, b, c);
						}
						catch (GPSException e) {
							System.out.println("Invailable Input");
							throw e;
						}
						tr.add(p);
					}
					index++;
					if(index==4)
						index=0;
				}
				if(index>0&&index<=3)
					throw new GPSException("Invailable Input");
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
	public Point lowestPoint() {
		if(tr.isEmpty())
			throw new GPSException("No Point in Track!");
		else {
			double m=tr.get(0).getElevation();
			int index=0;
			for(int i=1;i<tr.size();i++)
			{
				if(m>tr.get(i).getElevation())
				{
					m=tr.get(i).getElevation();
					index=i;
				}
			}
			return tr.get(index);
		}
	}
	public Point highestPoint() {
		if(tr.isEmpty())
			throw new GPSException("No Point in Track!");
		else {
			double m=tr.get(0).getElevation();
			int index=0;
			for(int i=1;i<tr.size();i++)
			{
				if(m<tr.get(i).getElevation())
				{
					m=tr.get(i).getElevation();
					index=i;
				}
			}
			return tr.get(index);
		}
	}
	public double totalDistance() {
		if(tr.size()<=1)
			throw new GPSException("Point not Enough in Track!");
		else {
			double total=0;
			for(int i=0;i<tr.size()-1;i++)
				total+=Point.greatCircleDistance(tr.get(i), tr.get(i+1));
			return total;
		}
	}
	public double averageSpeed() {
		if(tr.size()<=1)
			throw new GPSException("Point not Enough in Track!");
		else {
			double total=this.totalDistance();
			double time=ChronoUnit.SECONDS.between(tr.get(0).getTime(), tr.get(tr.size()-1).getTime());
			return total/time;
		}
	}
}