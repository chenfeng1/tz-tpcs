package com.tz.check;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocMethodCheck;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/30 13:55
 */
public class MethodLimitCheckTest extends BaseCheckTestSupport {

    private DefaultConfiguration checkConfig;

    @Before
    public void setUp()
    {
        checkConfig = createCheckConfig(MethodLimitCheck.class);
        checkConfig.addAttribute("max", "2");
    }

    @Test
    public void test1() throws Exception {
        final String[] expected = {
                "8: too many methods, only 2 are allowed",
        };
        verify(checkConfig, getPath("MockClassA.java"), expected);
    }

    @Test
    public void test2() throws Exception {
        final String[] expected = {
                "14: too many methods, only 2 are allowed",
        };
        verify(checkConfig, getPath("ProjectCase.java"), expected);
    }


}
