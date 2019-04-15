//create a Track object and test its functions

import java.io.FileNotFoundException;

public class TrackInfo {
	public static void main(String[] args) {
		if(args.length == 0){  
			System.out.println("No File to be Read!");  
			System.exit(1);
	    } 
		Track track1=new Track();
		try {
			track1.readFile(args[0]);
		}
		catch (FileNotFoundException e) {
			System.out.println("File not Found!");
			System.exit(1);
		}
		catch (GPSException e) {
			System.out.println("Data inavailalbe!");
			System.exit(1);
		}
		System.out.printf("%d points in track\n",track1.size());
		System.out.printf("Lowest point is (%f, %f), %.1f m\n",track1.lowestPoint().getLongitude(),track1.lowestPoint().getLatitude(),track1.lowestPoint().getElevation());
		System.out.printf("Highest point is (%f, %f), %.1f m\n",track1.highestPoint().getLongitude(),track1.highestPoint().getLatitude(),track1.highestPoint().getElevation());
		System.out.printf("Total distance = %.3f km\n",track1.totalDistance()/1000);
		System.out.printf("Average speed = %.3f m/s\n",track1.averageSpeed());
	}
}