package com.jiaoyiping.microframework;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/10
  * Time: 22:08
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.utils.ClassUtils;
import com.jiaoyiping.microframework.utils.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TestClassScanner {

    @Test
    public void getClassNames() throws IOException {
        TestClassScanner testClassScanner = new TestClassScanner();
        Collection<Class<?>> classes = testClassScanner.getClassByPackageName("com.jiaoyiping");
        for (Class<?> clazz : classes) {
            System.out.println(clazz.getName() + "      " + clazz.getSimpleName() + "       " + clazz.getSimpleName());
        }
        System.out.println(classes.size());

    }

    //测试，如何在运行的时候获取classpath下边的所有的类(提供包名)
    public Collection<Class<?>> getClassByPackageName(String packageName) throws IOException {
        Collection<Class<?>> classes = new ArrayList<>();
        Enumeration<URL> urls = ClassUtils.getClassLoader().getResources(packageName.replace(".", "/"));
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            //该资源是一个jar包(获取jar包中的所有的class文件并加载)
            if ("jar".equals(url.getProtocol())) {
                JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                JarFile jarFile = jarURLConnection.getJarFile();
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry entry = jarEntries.nextElement();
                    String jarEntryName = entry.getName();
                    if (jarEntryName.endsWith(".class")) {
                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
                        doAddClasses(classes, className);
                    }
                }


            }
            //该资源是一个文件(class文件或者是一个包含class文件的文件夹)
            if ("file".equals(url.getProtocol())) {
                String packagePath = url.getPath().replace("%20", " ");
                addClassFromDirectory(classes, packagePath, packageName);
            }
        }
        return classes;

    }

    //解析和加载Class文件
    public void addClassFromDirectory(Collection<Class<?>> classes, String packagePath, String packageName) {
        //获取包路径下的class文件或者目录
        //若是class文件则加载
        //若是目录则递归
        File[] files = new File(packagePath).listFiles((file) -> file.isDirectory() || (file.isFile() && file.getName().endsWith(".class")));
        for (File file : files) {
            if (file.isFile()) {
                //执行class文件的初始化操作
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClasses(classes, className);
                continue;
            }
            //若是文件夹，递归调用加载其中的class文件
            //需要获取子包和子包的路径
            String subPackagePath = file.getName();
            if (StringUtils.isNotEmpty(packagePath)) {
                subPackagePath = packagePath + "/" + subPackagePath;
            }
            String subPackageName = file.getName();
            if (StringUtils.isNotEmpty(subPackageName)) {
                subPackageName = packageName + "." + subPackageName;
            }
            addClassFromDirectory(classes, subPackagePath, subPackageName);
        }
    }

    private void doAddClasses(Collection<Class<?>> classes, String className) {
        Class<?> clazz = ClassUtils.loadClass(className, false);
        classes.add(clazz);
    }


}