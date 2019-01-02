package com.bank.ServiceRemitNormal.api;

import com.bank.ServiceRemitNormal.utils.BankResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ServiceRemitProducer")
public interface RemitProducerClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/remit/get",
            consumes = "application/json"
    )
    BankResult getRemit(@RequestParam(value = "remitInAccount")String remitInAccount,
                        @RequestParam(value = "remitId")String remitId);

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/remit/query",
            consumes = "application/json"
    )
    BankResult getRemitLogs();

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/user/remit//query/{remitId}",
            consumes = "application/json"
    )
    BankResult getOneRemitLog(@RequestParam(value = "id")String remitId);
}
