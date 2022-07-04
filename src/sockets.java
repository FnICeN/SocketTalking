import java.io.OutputStream;
public class sockets {
    public static OutputStream[] outStreams=new OutputStream[3];
    public static int index;
    public sockets(OutputStream outStream){
        outStreams[index]=outStream;
        index++;
        System.out.println("----------");
        System.out.println(index);
        System.out.println("----------");
    }
}
