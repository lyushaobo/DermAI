package com.dermai.project.common;

import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "Captcha Interface")
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    

}
