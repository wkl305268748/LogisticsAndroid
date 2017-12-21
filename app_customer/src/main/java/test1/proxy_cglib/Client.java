package test1.proxy_cglib;

/**
 * Created by Kenny on 2017/9/10.
 */

public class Client {
    public static void main(String[] args)
    {
        CglibSubject cglibSubject = new CglibSubject();
        RealSubject realSubject = (RealSubject)cglibSubject.getInstance(new RealSubject());
        realSubject.request();

        RealSubject2 realSubject2 = (RealSubject2)cglibSubject.getInstance(new RealSubject2());
        realSubject2.request2();
    }
}
