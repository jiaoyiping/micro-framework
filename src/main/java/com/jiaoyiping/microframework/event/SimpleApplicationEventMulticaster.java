package com.jiaoyiping.microframework.event;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/1
  * Time: 16:58
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.concurrent.Executor;

public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public Executor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    //TODO 在之前的实现中,使用到了任务队列来加速事件的发送
    private Executor taskExecutor;

    @Override
    public void multicastEvent(ApplicationEvent event) {
        getApplicationListeners(event).forEach(applicationListener -> {
            Executor executor = getTaskExecutor();
            if (executor == null) {
                invokeListener(applicationListener, event);

            } else {
                executor.execute(() -> invokeListener(applicationListener, event));
            }
        });

    }

    /**
     * 注意:当找到的Listener所持有的泛型和Event的类型不一致的时候,将会抛出异常
     *
     * @param listener
     * @param event
     */
    protected void invokeListener(ApplicationListener listener, ApplicationEvent event) {
        try {
            listener.onApplicationEvent(event);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}