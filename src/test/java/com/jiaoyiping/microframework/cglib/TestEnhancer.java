package com.jiaoyiping.microframework.cglib;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/10/6
  * Time: 19:15
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class TestEnhancer {

    public static Object createProxyObject(final Object object, Class<?> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            System.out.println("在方法之前");
            Object result = method.invoke(object, args);
            System.out.println("在方法之后\n");
            return result;
        });
        return enhancer.create();
    }

    public static void main(String[] args) {
        // 未实现接口的类的代理
        Person proxyPerson = (Person) TestEnhancer.createProxyObject(new Person(), Person.class);
        proxyPerson.say("慧与(中国)有限公司");
        proxyPerson.say("中国移动通信研究院");
        // 实现接口的类的代理
        Animal proxyDog = (Animal) TestEnhancer.createProxyObject(new Dog(), Dog.class);
        proxyDog.say("汪汪汪");

    }
}

class Person {
    public void say(String msg) {
        System.out.println("person.say:" + msg);
    }
}

interface Animal {
    void say(String msg);
}

class Dog implements Animal {

    @Override
    public void say(String msg) {
        System.out.println("dog.say:" + msg);
    }
}