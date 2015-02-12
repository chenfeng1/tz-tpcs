package com.tz.check;

import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocMethodCheck;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/30 13:51
 */
public class EntityPropColumnCheckTest extends BaseCheckTestSupport{

    private DefaultConfiguration checkConfig;

    @Before
    public void setUp()
    {
        checkConfig = createCheckConfig(EntityColumnCheck.class);
    }

    @Test
    public void test1() throws Exception {
        final String[] expected = {
                "69: @Column or @JoinXxx or @JoinTable is required",
        };
        verify(checkConfig, getPath("ProjectCase.java"), expected);
    }

    @Ignore
    @Test
    public void test2() throws Exception {
        final String[] expected = {
                "69: @Column or @JoinXxx or @JoinTable is required",
        };
        String path = new File("E:\\GitHub\\tz-tpcs\\trunk\\tpcs-employee\\src\\main\\java\\com\\tz\\tpcs\\web\\form\\EmployeeEditForm.java").getCanonicalPath();
        verify(checkConfig, getPath("ProjectCase.java"), expected);
    }

}
