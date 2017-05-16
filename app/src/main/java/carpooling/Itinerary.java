package carpooling;


import java.util.ArrayList;

public class Itinerary
{
    public static boolean sendItininerariesToServer(ArrayList liste)
    {
        liste.add(0, ServerCon.TYPE_SAVE_WP);
        //TODO add user identification

        String[] data = new String[liste.size()];
        liste.toArray(data);

        ServerCon con = new ServerCon();

        con.send(data);
        String[] res = con.receive();

        con.closeCon();

        if (res == null || res.length < 2)
            return false;

        //TODO handle answer

        return true;
    }

    public static boolean validateMatchBetweenUsers()
    {

        ServerCon con = new ServerCon();
        con.send(new String[]{ServerCon.TYPE_VALIDATE_MATCH});
        String[] res = con.receive();
        con.closeCon();

        if (res == null || res.length != 1)
            return false;
        if (Integer.parseInt(res[1]) == 1)
            return true;

        return false;
    }


}
