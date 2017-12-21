package test1.proxy_static;

/**
 * Created by Kenny on 2017/9/10.
 */

public class ProxySubject implements ISubject {
    //代理角色对象内部含有对真实对象的引用
    private ISubject realSubject;

    public ProxySubject(ISubject realSubject){
        this.realSubject = realSubject;
    }

    @Override
    public void request()
    {
        //真实角色所完成的事情
        realSubject.request();
        //在真实角色操作之后所附加的操作
        log(realSubject.getClass().getName());
    }

    private void log(String className)
    {
        System.out.println("代理AOP切入--打印log--"+className);
    }
}
