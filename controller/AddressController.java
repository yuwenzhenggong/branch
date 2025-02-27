package com.cy.store.controller;

import com.cy.store.model.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("addresses")
@RestController
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public BaseResponse<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new BaseResponse<>(OK);
    }

    @RequestMapping({"","/"})
    public BaseResponse<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new BaseResponse<>(OK,data);
    }

    //RestFul风格的请求编写
    @RequestMapping("{aid}/set_default")
    public BaseResponse<Void> setDefault(
            @PathVariable("aid") Integer aid,HttpSession session) {
        addressService.setDefault(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new BaseResponse<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public BaseResponse<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.delete(
                aid,
                getUidFromSession(session),
                getUsernameFromSession(session));
        return new BaseResponse<>(OK);
    }
}
