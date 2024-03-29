package com.dermai.framework.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static com.google.code.kaptcha.Constants.*;

/**
 * Captcha Config
 */
@Configuration
public class CaptchaConfiguration {
    @Bean(name = "captchaProducer")
    public DefaultKaptcha getKaptchaBean() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // If there is a border, the default is true,  can set yes, no
        properties.setProperty(KAPTCHA_BORDER, "yes");
        // The default Color for CAPTCHA text characters is color.black
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        // By default, the CAPTCHA image width is 200
        properties.setProperty(KAPTCHA_IMAGE_WIDTH, "160");
        // By default, the height of the CAPTCHA image is 50
        properties.setProperty(KAPTCHA_IMAGE_HEIGHT, "60");
        // The default CAPTCHA text character size is 40
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_SIZE, "38");
        // KAPTCHA_SESSION_KEY
        properties.setProperty(KAPTCHA_SESSION_CONFIG_KEY, "kaptchaCode");
        // By default, the length of the CAPTCHA text is 5
        properties.setProperty(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        // CAPTCHA text font style, the default is new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
        properties.setProperty(KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Arial,Courier");
        // Picture Style WaterRipple: com.google.code.kaptcha.impl.WaterRipple
        //               FishEyeGimpy: com.google.code.kaptcha.impl.FishEyeGimpy
        //               ShadowGimpy: com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty(KAPTCHA_OBSCURIFICATOR_IMPL, "com.google.code.kaptcha.impl.ShadowGimpy");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
