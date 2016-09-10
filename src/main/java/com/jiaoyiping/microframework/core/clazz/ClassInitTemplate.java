package com.jiaoyiping.microframework.core.clazz;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/4
  * Time: 22:57
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.utils.ClassUtils;
import com.jiaoyiping.microframework.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

//TODO 程序运行时如何根据包名和classpath获取该包下所有的class文件?
public class ClassInitTemplate {
    private static final Logger logger = LoggerFactory.getLogger(ClassInitTemplate.class);

    protected final String packageName;

    protected ClassInitTemplate(String packageName) {
        this.packageName = packageName;
    }

    public Collection<Class<?>> getClasses() {
        Collection<Class<?>> result = new ArrayList<>();
        try {
            Enumeration<URL> urls = ClassUtils.getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        String packagePath = url.getPath().replace("%20", " ");
                        loadClassFromDirectory(result, packagePath, packageName);
                    } else if ("jar".equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            if (jarEntry.getName().endsWith(".class")) {
                                doAddClass(result, jarEntry.getName().replace("/", "."));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从目录中加载class文件
     *
     * @param result
     * @param packagePath
     * @param packageName
     */
    private void loadClassFromDirectory(Collection<Class<?>> result, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles((file) -> file.isDirectory() || (file.isFile() && file.getName().endsWith(".class")));
        for (File file : files) {
            if (file.isFile()) {
                String className = file.getName().substring(0, file.getName().lastIndexOf("."));
                if (StringUtils.isNotEmpty(packageName)) {
                    className = packageName + "." + className;
                }
                doAddClass(result, className);
                continue;
            }
            String subPackagePath = file.getName();
            if (StringUtils.isNotEmpty(packagePath)) {
                subPackagePath = packagePath + "/" + subPackagePath;
            }
            String subPackageNamem = file.getName();
            if (StringUtils.isNotEmpty(packageName)) {
                subPackageNamem = packageName + "." + subPackageNamem;
            }
            loadClassFromDirectory(result, subPackagePath, subPackageNamem);


        }
    }

    /**
     * 将class文件加载并添加到指定的集合中
     *
     * @param classes   被加载的class文件的集合
     * @param className class文件的完全限定名称
     */
    private void doAddClass(Collection<Class<?>> classes, String className) {
        Class<?> clazz = ClassUtils.loadClass(className, false);
        classes.add(clazz);
        logger.debug("load class:" + className);
    }
}