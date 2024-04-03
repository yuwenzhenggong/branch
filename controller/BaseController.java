package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.util.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层里面类的基类
 */
public class BaseController {



    /**
     * 1.@ExceptionHandler表示该方法用于处理捕获抛出的异常
     * 2.什么样的异常才会被这个方法处理呢?所以需要ServiceException.class,这样
     * 的话只要是抛出ServiceException异常就会被拦截到handleException方法,此
     * 时handleException方法就是请求处理方法,返回值就是需要传递给前端的数据
     * 3.被ExceptionHandler修饰后如果项目发生异常,那么异常对象就会被自动传递
     * 给此方法的参数列表上,所以形参就需要写Throwable e用来接收异常对象
     */
    @ExceptionHandler({ServiceException.class})
    public BaseResponse<Void> handleException(Throwable e) {
        BaseResponse<Void> result = new BaseResponse<>(e);
        if (e instanceof PhoneDuplicatedException) {
            result.setSuccess(false);
            result.setMsg("用户名已经被占用的异常");
        } else if (e instanceof PhoneNotFoundException) {
            result.setSuccess(false);
            result.setMsg("用户数据不存在的异常");
        } else if (e instanceof PasswordNotMatchException) {
            result.setSuccess(false);
            result.setMsg("用户名密码错误的异常");
        } else if (e instanceof AddressCountLimitException) {
            result.setSuccess(false);
            result.setMsg("用户的收货地址超出上限的异常");
        } else if (e instanceof AddressNotFoundException) {
            result.setSuccess(false);
            result.setMsg("用户的收货地址数据不存在的异常");
        } else if (e instanceof AccessDeniedException) {
            result.setSuccess(false);
            result.setMsg("收货地址数据非法访问的异常");
        } else if (e instanceof ProductNotFoundException) {
            result.setSuccess(false);
            result.setMsg("访问的商品数据不存在的异常");
        } else if (e instanceof CartNotFoundException) {
            result.setSuccess(false);
            result.setMsg("购物车表不存在该商品的异常");
        } else if (e instanceof InsertException) {
            result.setSuccess(false);
            result.setMsg("插入数据时产生未知的异常");
        } else if (e instanceof UpdateException) {
            result.setSuccess(false);
            result.setMsg("更新数据时产生未知的异常");
        } else if (e instanceof DeleteException) {
            result.setSuccess(false);
            result.setMsg("删除数据时产生未知的异常");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录的用户uid的值
     */
    public final Integer getUidFromSession(HttpSession session) {
        //getAttribute返回的是Object对象,需要转换为字符串再转换为包装类
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    public final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("phone").toString();
    }
}
