package test1.proxy_dynamic;

/**
 * Created by Kenny on 2017/9/10.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 该代理类的内部属性是Object类型，实际使用的时候通过该类的构造方法传递进来一个对象。
 * 该类实现了invoke()方法，该方法中的method.invoke()其实就是调用被代理对象的将要执行的方法，
 * 方法参数sub表示该方法从属于sub。
 * 通过动态代理类，我们可以在执行真实对象的方法前后加入自己的一些额外方法
 *
 */
public class ProxySubject implements InvocationHandler {

    //对真实对象的引用
    private Object sub;

    public static Object getInstance(Object sub)
    {
        // 生成代理
        // 动态生成一个类（实现了指定的接口），生成类的对象，转换成接口类型
        return Proxy.newProxyInstance(sub.getClass().getClassLoader(),
                sub.getClass().getInterfaces(), new ProxySubject(sub));
    }

    private ProxySubject(Object sub){
        this.sub = sub;
    }

    // 调用方法时，转移给handler接管，由其中的invoke()方法实际完成方法执行
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //通过反射来调用方法
        method.invoke(sub, args);

        log(sub.getClass().getName());
        return null;
    }

    private void log(String className)
    {
        System.out.println("代理AOP切入--打印log--"+className);
    }
}
