package test1.proxy_cglib;

/**
 * Created by Kenny on 2017/9/10.
 */


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibSubject implements MethodInterceptor {

    private Object sub;

    /**
     * 创建代理对象
     *
     * @param sub
     * @return
     */
    public Object getInstance(Object sub) {
        this.sub = sub;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.sub.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    // 回调方法
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        proxy.invokeSuper(obj, args);
        log(sub.getClass().getName());
        return null;
    }

    private void log(String className)
    {
        System.out.println("代理AOP切入--打印log--"+className);
    }
}
