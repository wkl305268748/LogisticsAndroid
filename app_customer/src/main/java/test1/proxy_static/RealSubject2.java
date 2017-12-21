package test1.proxy_static;

/**
 * Created by Kenny on 2017/9/10.
 */

public class RealSubject2 implements ISubject2 {
    @Override
    public void request2() {
        System.out.println("From Real Subject2!");
    }
}
