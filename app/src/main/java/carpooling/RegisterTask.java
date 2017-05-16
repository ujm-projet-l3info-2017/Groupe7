package carpooling;

/**
 * Created by yassine on 4/28/17.
 */

import android.os.AsyncTask;

public class RegisterTask extends AsyncTask<String, Void, Boolean>
{
    /* TODO: sha256 -> bcrypt() */
    @Override
    protected Boolean doInBackground(String... params)
    {
        String pseudo = params[0];
        String passwd = params[1];
        String mail = params[2];
        String tel = params[3];
        String hash;
        ServerCon con;
        String[] res;

        if (pseudo.trim().isEmpty() || passwd.trim().isEmpty() || mail.trim().isEmpty() || tel.trim().isEmpty())
            return false;
        if (pseudo.length() >= 32)
            return false;

        hash = Common.hashString(passwd, "SHA-256");
        con = new ServerCon();
        con.send(new String[]{ServerCon.TYPE_REGISTER, pseudo, hash, mail, tel});
        res = con.receive();
        con.closeCon();

        if (res == null || res.length != 2)
            return false;
        if (Integer.parseInt(res[1]) == 1)
            return true;
        return false;
    }
}
