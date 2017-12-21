package test1.proxy;

/**
 * Created by Kenny on 2017/9/10.
 */

public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("From Real Subject1!");
    }
}
