package com.jiaoyiping.microframework.mvc;
 /*
  * Created with Intellij IDEA
  * USER: 焦一平
  * Mail: jiaoyiping@gmail.com
  * Date: 2016/9/13
  * Time: 7:34
  * To change this template use File | Settings | Editor | File and Code Templates
 */

import com.jiaoyiping.microframework.mvc.bean.Result;
import com.jiaoyiping.microframework.mvc.bean.View;
import com.jiaoyiping.microframework.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultViewResolver implements ViewResolver {

    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionResult) {
        if (actionResult instanceof View) {
            // TODO: 2016/12/28 没有实现视图解析相关的功能
        } else if (actionResult instanceof Result) {
            Result result = (Result) actionResult;
            if (!result.isSuccess()) {
                try {
                    response.sendError(result.getCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                WebUtils.writeJson(request, response, result.getData());
            }
        }
    }
}