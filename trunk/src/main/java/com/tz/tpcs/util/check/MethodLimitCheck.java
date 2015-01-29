package com.tz.tpcs.util.check;


import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * 方法数限制 Check 类
 * http://checkstyle.sourceforge.net/writingchecks.html
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/29 17:05
 */
public class MethodLimitCheck extends Check {

    private static final int DEFAULT_MAX = 30;
    private int max = DEFAULT_MAX;

    @Override
    public int[] getDefaultTokens() {
        //TreeWalker will only call visitToken for these token types
        //需要检查的类和接口
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        // find the OBJBLOCK node below the CLASS_DEF/INTERFACE_DEF
        //获得类体部分
        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

        // count the number of direct children of the OBJBLOCK
        // that are METHOD_DEFS
        //获得方法计数
        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);

        // report error if limit is reached
        if (methodDefs > this.max) {
            String message = "too many methods, only " + this.max + " are allowed";
            log(ast.getLineNo(), message);
        }
    }

    // code from above omitted for brevity
    public void setMax(int limit)
    {
        max = limit;
    }
}
