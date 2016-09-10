package com.jiaoyiping.microframework;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/11
  * Time: 0:19
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.core.clazz.ClassInitTemplate;
import org.junit.Test;

import java.util.Collection;

public class TestClassInitTemplate {
    @Test
    public void testLoadClass() {
        TestTemplate testTemplate = new TestTemplate("com.jiaoyiping");
        Collection<Class<?>> classes = testTemplate.getClasses();
        System.out.println(classes.size());
        for (Class<?> clazz : classes) {
            System.out.println(clazz.getName());
        }
    }

}

class TestTemplate extends ClassInitTemplate {

    protected TestTemplate(String packageName) {
        super(packageName);
    }
}