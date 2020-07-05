package com.cy.zfb.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.cy.zfb.config.AlipayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @projectName:zfb
 * @see:com.cy.zfb.controller
 * @author:chengyang
 * @createTime:2020/7/3 10:09 下午
 * @version:1.0
 */
@Controller
@Slf4j
public class IndexController {

    @ResponseBody
    @RequestMapping("/pay")
    public String Pay(String out_trade_no,
                      String subject,
                      String total_amount,
                      String body,
                      String timeout_express,
                      String produce_code) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.URL,
                alipayConfig.APPID,
                alipayConfig.RSA_PRIVATE_KEY,
                alipayConfig.FORMAT,
                alipayConfig.CHARSET,
                alipayConfig.ALIPAY_PUBLIC_KEY,
                alipayConfig.SIGNTYPE
        );

        //请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //封装数据
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(produce_code);
        request.setBizModel(model);
        request.setNotifyUrl(alipayConfig.return_url);

        String form = alipayClient.pageExecute(request).getBody();
        log.info(form);
        return form;
    }

}
