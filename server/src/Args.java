/**
 * Created by yassine on 4/1/17.
 */

import java.io.*;

public class Args
{
    private BufferedReader in;
    private String separator;
    private String[] args;
    private Debug dbg;
    private int size;
    private String rawData;

    Args(BufferedReader in, String separator)
    {
        this.in = in;
        this.separator = separator;
        this.args = new String[512];
        this.dbg = new Debug("ARGS");
    }

    public String[] getArgs()
    {
        String line = null;
        try
        {
            line = in.readLine();
            if (line == null)
                return null;
            rawData = line;
            args = line.split(separator);
            for (size = 0; size < this.args.length && args[size] != null; size++);

        }
        catch(IOException ioe)
        {
            dbg.error("IOException caught: '" + ioe.getMessage() + "'");
        }

        return args;
    }

    public String get(int i)
    {
        if (i < size)
            return args[i];
        return null;
    }

    public String getRawData()
    {
        return rawData;
    }

    public int size()
    {
        return this.size;
    }

    @Override
    public String toString()
    {
        if (size > 0)
            return "type: " + args[0] + " size: " + size;
        return "empty args";
    }
}
