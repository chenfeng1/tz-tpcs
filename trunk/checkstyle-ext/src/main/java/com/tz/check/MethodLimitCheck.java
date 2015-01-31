package com.tz.check;


import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * 方法数限制 Check 类 (HelloWorld)
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/29 17:05
 */
public class MethodLimitCheck extends Check {

    private static final int DEFAULT_MAX = 30;
    private int max = DEFAULT_MAX;

    @Override
    public int[] getDefaultTokens() {
        //需要检查的 类和接口
        return new int[]{TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        //获得类体部分
        DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
        //获得方法计数
        int methodDefs = objBlock.getChildCount(TokenTypes.METHOD_DEF);
        //如果超出最大数
        if (methodDefs > this.max) {
            String message = "too many methods, only " + this.max + " are allowed";
            log(ast.getLineNo(), message);
        }
    }

    //setMax
    public void setMax(int limit)
    {
        max = limit;
    }
}
