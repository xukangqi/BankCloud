package com.bank.feignuserconsumer.service;

import com.bank.feignuserconsumer.pojo.BankUser;
import com.bank.feignuserconsumer.service.hystrix.UserOtherHystrix;
import com.bank.feignuserconsumer.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(value = "provider-user-other", fallback = UserOtherHystrix.class)
public interface IUserOtherService {
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    @ResponseBody
    public BankResult delete(@RequestParam(value = "username") String username);

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public BankResult register(BankUser bankUser);

    @RequestMapping(value = "/user/changeinfo", method = RequestMethod.POST)
    @ResponseBody
    public BankResult changeinfo(BankUser user);

    @RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
    @ResponseBody
    public BankResult changepassword(@RequestParam("username") String username, @RequestParam("oldpassword") String oldpassword,
                                     @RequestParam("newpassword") String newpassword);

}
