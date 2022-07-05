import java.io.InputStream;
import java.io.OutputStream;
public class sockets {
    public static OutputStream[] outStreams=new OutputStream[10];
    public static int index_serverOut;
    public static InputStream serverInStream;

    public sockets(OutputStream outStream){
        outStreams[index_serverOut++]=outStream;
    }
}
