package com.tz.check;

import com.google.common.collect.Lists;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.DefaultLogger;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.Configuration;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public abstract class BaseCheckTestSupport {
    /**
     * a brief logger that only display info about errors
     */
    protected static class BriefLogger
            extends DefaultLogger {
        public BriefLogger(OutputStream out) {
            super(out, true);
        }

        @Override
        public void auditStarted(AuditEvent evt) {
        }

        @Override
        public void fileFinished(AuditEvent evt) {
        }

        @Override
        public void fileStarted(AuditEvent evt) {
        }
    }

    protected final ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
    protected final PrintStream stream = new PrintStream(BAOS);

    public static DefaultConfiguration createCheckConfig(Class<?> clazz) {
        final DefaultConfiguration checkConfig =
                new DefaultConfiguration(clazz.getName());
        return checkConfig;
    }

    protected Checker createChecker(Configuration checkConfig)
            throws Exception {
        final DefaultConfiguration dc = createCheckerConfig(checkConfig);
        final Checker c = new Checker();
        // make sure the tests always run with english error messages
        // so the tests don't fail in supported locales like german
        final Locale locale = Locale.ENGLISH;
        c.setLocaleCountry(locale.getCountry());
        c.setLocaleLanguage(locale.getLanguage());
        c.setModuleClassLoader(Thread.currentThread().getContextClassLoader());
        c.configure(dc);
        c.addListener(new BriefLogger(stream));
        return c;
    }

    protected DefaultConfiguration createCheckerConfig(Configuration config) {
        final DefaultConfiguration dc =
                new DefaultConfiguration("configuration");
        final DefaultConfiguration twConf = createCheckConfig(TreeWalker.class);
        // make sure that the tests always run with this charset
        dc.addAttribute("charset", "UTF-8");
        dc.addChild(twConf);
        twConf.addChild(config);
        return dc;
    }

    protected static String getPath(String filename)
            throws IOException {
//        String path = new File("checkstyle-ext/src/test/java/com/tz/check/" + filename).getCanonicalPath();
        String path = new File("src/test/java/com/tz/check/" + filename).getCanonicalPath();
        System.out.println("path:"+path);
        return path;
    }

    protected void verify(Configuration aConfig, String fileName, String[] expected)
            throws Exception {
        verify(createChecker(aConfig), fileName, fileName, expected);
    }

    protected void verify(Checker c,
                          String processedFilename,
                          String messageFileName,
                          String[] expected)
            throws Exception {
        verify(c,
                new File[]{new File(processedFilename)},
                messageFileName, expected);
    }

    protected void verify(Checker c,
                          File[] processedFiles,
                          String messageFileName,
                          String[] expected)
            throws Exception {
        stream.flush();
        final List<File> theFiles = Lists.newArrayList();
        Collections.addAll(theFiles, processedFiles);
        final int errs = c.process(theFiles);

        // process each of the lines
        final ByteArrayInputStream bais =
                new ByteArrayInputStream(BAOS.toByteArray());
        final LineNumberReader lnr =
                new LineNumberReader(new InputStreamReader(bais));

        for (int i = 0; i < expected.length; i++) {
            final String expectedResult = messageFileName + ":" + expected[i];
            final String actual = lnr.readLine();
            assertEquals("error message " + i, expectedResult, actual);
        }

        assertEquals("unexpected output: " + lnr.readLine(),
                expected.length, errs);
        c.destroy();
    }

}
