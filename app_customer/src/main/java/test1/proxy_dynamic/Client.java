package test1.proxy_dynamic;


/**
 * Created by Kenny on 2017/9/10.
 */

public class Client {
    public static void main(String[] args)
    {
        ISubject realSubject = (ISubject) ProxySubject.getInstance(new RealSubject());
        realSubject.request();

        ISubject2 realSubject2 = (ISubject2)ProxySubject.getInstance(new RealSubject2());
        realSubject2.request2();
    }
}
