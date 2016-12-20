package com.jiaoyiping.microframework.event;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/12/1
  * Time: 16:17
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import java.util.EventObject;

public abstract class ApplicationEvent extends EventObject {
    private static final long serialVersionUID = 5762764206978050329L;
    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}