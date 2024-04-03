package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.BaseResponse;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public BaseResponse<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        cartService.addToCart(
                getUidFromSession(session),
                pid,
                amount,
                getUsernameFromSession(session));
        return new BaseResponse<Void>(OK);
    }

    @RequestMapping({"", "/"})
    public BaseResponse<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new BaseResponse<List<CartVO>>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public BaseResponse<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(
                cid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new BaseResponse<Integer>(OK, data);
    }

    @RequestMapping("list")
    public BaseResponse<List<CartVO>> findVOByCids(Integer[] cids, HttpSession session) {
        List<CartVO> data = cartService.getVOByCids(getUidFromSession(session), cids);
        return new BaseResponse<>(OK, data);
    }
}
