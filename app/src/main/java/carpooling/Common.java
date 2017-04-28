package carpooling;

import java.security.MessageDigest;

/**
 * Created by yassine on 4/28/17.
 */

public class Common
{
    public static String hashString(String message, String algorithm)
    {
        MessageDigest digest = null;
        byte[] hashedBytes = null;

        try
        {
            digest = MessageDigest.getInstance(algorithm);
            hashedBytes = digest.digest(message.getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return convertByteArrayToHexString(hashedBytes);
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes)
    {
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < arrayBytes.length; i++)
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        return stringBuffer.toString();
    }
}

