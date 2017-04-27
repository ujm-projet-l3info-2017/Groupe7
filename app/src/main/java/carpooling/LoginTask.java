package carpooling;

/**
 * Created by yassine on 4/25/17.
 */

import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String, Void, Boolean>
{
    @Override
    protected Boolean doInBackground(String... params)
    {
        String pseudo = params[0];
        String passwd = params[1];
        ServerCon con = new ServerCon();
        String[] res;

        if (pseudo.trim().isEmpty() || passwd.trim().isEmpty())
            return false;
        if (pseudo.length() >= 32)
            return false;

        con.send(new String[]{ServerCon.TYPE_AUTH, pseudo, passwd});
        res = con.receive();
        con.closeCon();

        if (res == null || res.length != 2)
            return false;
        if (Integer.parseInt(res[1]) == 1)
            return true;
        return false;
    }
}
