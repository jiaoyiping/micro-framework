package com.jiaoyiping.microframework.aop;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/8
  * Time: 15:59
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class BaseAspect implements MethodInterceptor {
    private static Logger logger = LoggerFactory.getLogger(MethodInterceptor.class);

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object proxy, Method methodTarget, Object[] args, MethodProxy methodProxy) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug("");
        }
        return advice(new Pointcut(methodTarget, methodProxy), proxy, args);
    }

    /*public final Object advice(Pointcut pointcut, Object proxy, Object[] args) {


        return null;
    }*/
    protected abstract Object advice(Pointcut pointcut, Object proxy, Object[] args);

    protected class Pointcut {

        private Method methodTarget;
        private MethodProxy methodProxy;

        public Pointcut(Method methodTarget, MethodProxy methodProxy) {
            this.methodTarget = methodTarget;
            this.methodProxy = methodProxy;
        }

        public Method getMethodTarget() {
            return methodTarget;
        }

        public MethodProxy getMethodProxy() {
            return methodProxy;
        }

        public Object invoke(Object proxy, Object[] args) {
            Object result = null;
            try {
                result = methodProxy.invokeSuper(proxy, args);
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    protected void before() {

    }

    protected void after() {

    }

}