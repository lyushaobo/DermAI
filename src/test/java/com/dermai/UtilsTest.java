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
        String s = "In addition, Pfizer¡¯s ESG strategy, which is integrated into our corporate strategy, focuses on six areas where we see opportunities to create a \n" +
                "meaningful impact: product innovation; equitable access and pricing; product quality and safety; diversity, equity and inclusion; climate change; \n" +
                "and business ethics.\n" +
                "We are committed to strategically capitalizing on growth opportunities, primarily by advancing our own product pipeline and maximizing the value \n" +
                "of our existing products, but also through various business development activities. We view our business development activity as an enabler of \n" +
                "our strategies and seek to generate growth by pursuing opportunities and transactions that have the potential to strengthen our business and our " +
                "capabilities. We assess our business, assets and scientific capabilities/portfolio as part of our regular, ongoing portfolio review process and also \n" +
                "continue to consider business development activities that will help advance our business strategy.\n" +
                "On December 14, 2023, we completed our acquisition of Seagen, a global biotechnology company that discovers, develops and commercializes \n" +
                "transformative cancer medicines. With the addition of Seagen¡¯s pipeline and its four in-line medicines (Padcev, Adcetris, Tukysa and Tivdak), \n" +
                "Pfizer¡¯s oncology portfolio spans multiple modalities, including ADCs, small molecules, bispecifics and other immunotherapies. In addition to the \n" +
                "acquisition of Seagen, our significant recent business development activities in 2023 include, among others, the September 2023 divestiture of \n" +
                "our early-stage rare disease gene therapy portfolio to Alexion. For a further discussion of our strategy and our business development initiatives, \n" +
                "see the Overview of Our Performance, Operating Environment, Strategy and Outlook section within MD&A and Note 2.";

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
