package test1.proxy;

/**
 * Created by Kenny on 2017/9/10.
 */

public class Client {
    public static void main(String[] args)
    {
        new ProxySubject(new RealSubject()).request();
    }
}
