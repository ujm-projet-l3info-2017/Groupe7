import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yassine on 3/30/17.
 */
public class Debug
{
    private String src;

    Debug(String src)
    {
        this.src = src;
    }

    public void info(String info)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd hh:mm:ss");
        String date = sdf.format(new Date());

        System.out.printf("[+] %s: %s %s\n", src, date, info);
    }

    public void warning(String warning)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd hh:mm:ss");
        String date = sdf.format(new Date());

        System.out.printf("[!] %s: %s %s\n", src, date, warning);
    }

    public void error(String error)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd hh:mm:ss");
        String date = sdf.format(new Date());

        System.out.printf("[-] %s: %s %s\n", src, date, error);
    }
}
