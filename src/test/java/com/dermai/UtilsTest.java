package com.dermai;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

/**
 * @author Shaobo
 */
public class UtilsTest {
    @Test
    void StrUtilsUpperFirst() {
        System.out.println(StrUtil.upperFirst("abc adskjdflkda sss"));
    }

    @Test
    void deleteEnter() {
        String s = "In particular, forward-looking information in this Form 10-K includes statements relating to specific future actions, performance and effects, \n" +
                "including, among others, the expected benefits of the organizational changes to our operations; our anticipated operating and financial \n" +
                "performance; our ongoing efforts to respond to COVID-19, including our plans and expectations regarding Comirnaty and Paxlovid, and any \n" +
                "potential future vaccines or treatments, including anticipated revenue and expectations for the commercial market for Comirnaty and Paxlovid; \n" +
                "our expectations regarding the impact of COVID-19 on our business; expected patent terms; the expected impact of patent expiries and generic \n" +
                "and biosimilar competition; the expected pricing pressures on our products and the anticipated impact to our business; the benefits expected \n" +
                "from our business development transactions, including our December 2023 acquisition of Seagen; our anticipated liquidity position; the \n" +
                "anticipated costs, savings and potential benefits from certain of our initiatives, including our enterprise-wide Realigning our Cost Base program, \n" +
                "which we launched in October 2023, and our Transforming to a More Focused Company program; our expectations regarding the impact from \n" +
                "the 2023 tornado on our manufacturing facility in Rocky Mount, NC; our greenhouse gas emission reduction goals; our planned capital spending; \n" +
                "and our capital allocation framework.";

        System.out.println(s.replace("\n",""));
    }

    @Test
    void snowFlakeTest() {
        Snowflake snowflake1 = IdUtil.getSnowflake(1);
        Snowflake snowflake2 = IdUtil.getSnowflake(2);
        for (int i = 0; i <100; i++) {
            System.out.println(snowflake1.nextIdStr());
            System.out.println(snowflake2.nextIdStr());
        }



    }
}
