import java.util.ArrayList;

/**
 * Created by yassine on 5/13/17.
 */
public class Coordinate
{
    /* Latitude & longitude */
    private double lat;
    private double lon;

    Coordinate(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat()
    {
        return this.lat;
    }

    public double getLon()
    {
        return this.lon;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2)
    {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;

        return dist;
    }

    /**
     * Computes distance, between this coordinate and c.
     * @param c Coordinate of the second location.
     * @return Distance in kilometer between both coordinates.
     */
    public double distance(Coordinate c)
    {
        double theta = this.lon - c.getLon();
        double dist = Math.sin(deg2rad(this.lat)) * Math.sin(deg2rad(c.getLat())) + Math.cos(deg2rad(this.lat)) * Math.cos(deg2rad(c.getLat())) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;

        return dist;
    }

    public static ArrayList<Coordinate> str2coordinates(String cStr)
    {
        int i = 0;

        if (cStr == null || cStr.isEmpty())
            return null;

        ArrayList<Coordinate> cList = new ArrayList<Coordinate>();

        String[] list = cStr.replace("[", "").replace("]", "").replace(" ", "").split(",");

        for(i = 0; i < list.length; i+=2)
        {
            double lat = Double.parseDouble(list[i]);
            double lon = Double.parseDouble(list[i+1]);
            cList.add(new Coordinate(lat, lon));
        }

        return cList;
    }

    public static String coordinates2str(ArrayList<Coordinate> cList)
    {
        String s = "[";
        int i;

        for(i = 0; i < cList.size(); i++)
            if ((i+1) != cList.size())
               s += "[" + cList.get(i).getLat() + "," + cList.get(i).getLon() + "], ";
            else
                s += "[" + cList.get(i).getLat() + "," + cList.get(i).getLon() + "]";
            s += "]";

            return s;
    }

    private static double deg2rad(double deg)
    {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad)
    {
        return (rad * 180 / Math.PI);
    }
}
