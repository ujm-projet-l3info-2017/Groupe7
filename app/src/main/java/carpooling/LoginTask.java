package carpooling;

/**
 * Created by yassine on 4/25/17.
 */

import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String, Void, Boolean>
{
    /* TODO: sha256 -> bcrypt() */
    @Override
    protected Boolean doInBackground(String... params)
    {
        String pseudo = params[0];
        String passwd = params[1];
        String hash;
        ServerCon con;
        String[] res;

        if (pseudo.trim().isEmpty() || passwd.trim().isEmpty())
            return false;
        if (pseudo.length() >= 32)
            return false;

        hash = Common.hashString(passwd, "SHA-256");
        con = new ServerCon();
        con.send(new String[]{ServerCon.TYPE_AUTH, pseudo, hash});
        res = con.receive();
        con.closeCon();

        if (res == null || res.length != 2)
            return false;
        if (Integer.parseInt(res[1]) == 1)
            return true;
        return false;
    }
}
