package com.dermai.project.common;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.dermai.common.constants.RedisConstants;
import com.dermai.framework.redis.RedisCache;
import com.dermai.framework.web.domain.AjaxResult;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "Captcha Interface")
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Autowired
    private RedisCache redisCache;

    /**
     * generate captcha
     * @return AjaxResult
     */
    @ApiOperation("get captcha image")
    @GetMapping("/captchaImage")
    public AjaxResult getCode() {
        AjaxResult ajax = AjaxResult.success();

        // TODO config captcha enabled status
        ajax.put("captchaEnabled", true);

        // uniqe uuid for user
        String uuid = IdUtil.fastSimpleUUID();
        String captchaKey = RedisConstants.CAPTCHA_CODE_KEY + uuid;

        // generate captcha code and image
        String code = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(code);

        // setting redis cache
        redisCache.setCacheObject(captchaKey, code, RedisConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // Convert stream information to write
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}
