package test1.proxy_static;

/**
 * Created by Kenny on 2017/9/10.
 */

public class Client {
    public static void main(String[] args)
    {
        new ProxySubject(new RealSubject()).request();

        new ProxySubject2(new RealSubject2()).request2();
    }
}
