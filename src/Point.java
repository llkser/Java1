import java.time.ZonedDateTime;

import static java.lang.Math.*;

import java.text.DecimalFormat;

/**
 * Represents a point in space and time, recorded by a GPS sensor.
 *
 * @author Nick Efford & Kang Liu
 */
public class Point {
  // Constants useful for bounds checking, etc

  private static final double MIN_LONGITUDE = -180.0;
  private static final double MAX_LONGITUDE = 180.0;
  private static final double MIN_LATITUDE = -90.0;
  private static final double MAX_LATITUDE = 90.0;
  private static final double MEAN_EARTH_RADIUS = 6.371009e+6;

  // TODO: Define fields for time, longitude, latitude and elevation
  private double longitude, latitude, elevation;
  private ZonedDateTime datetime;
  
  // TODO: Define a constructor
  Point(ZonedDateTime d, double lo, double la, double el)
  {
	  if(lo>MAX_LONGITUDE||lo<MIN_LONGITUDE||la>MAX_LATITUDE||la<MIN_LATITUDE)
		  throw new GPSException("Invailable Input");
	  else {
		  longitude=lo;
		  latitude=la;
		  elevation=el;
	  }
	  datetime=d;
  }
  
  // TODO: Define getters for the fields
  double getLongitude()
  {
	  return longitude;
  }
  double getLatitude()
  {
	  return latitude;
  }
  double getElevation()
  {
	  return elevation;
  }
  ZonedDateTime getTime()
  {
	  return datetime;
  }
  // TODO: Define a toString() method that meets requirements
  public String toString()
  {
	  DecimalFormat df = new DecimalFormat("0.00000");
	  String s="(";
	  s+="".valueOf(df.format(longitude));
	  s+=", ";
	  s+="".valueOf(df.format(latitude));
	  s+="), ";
	  s+="".valueOf(elevation);
	  s+=" m";
	  return s;
  }
  // Do not alter anything beneath this comment

  /**
   * Computes the great-circle distance or orthodromic distance between
   * two points on a spherical surface, using Vincenty's formula.
   *
   * @param p First point
   * @param q Second point
   * @return Distance between the points, in metres
   */
  public static double greatCircleDistance(Point p, Point q) {
    double phi1 = toRadians(p.getLatitude());
    double phi2 = toRadians(q.getLatitude());

    double lambda1 = toRadians(p.getLongitude());
    double lambda2 = toRadians(q.getLongitude());
    double delta = abs(lambda1 - lambda2);

    double firstTerm = cos(phi2)*sin(delta);
    double secondTerm = cos(phi1)*sin(phi2) - sin(phi1)*cos(phi2)*cos(delta);
    double top = sqrt(firstTerm*firstTerm + secondTerm*secondTerm);

    double bottom = sin(phi1)*sin(phi2) + cos(phi1)*cos(phi2)*cos(delta);

    return MEAN_EARTH_RADIUS * atan2(top, bottom);
  }
}
