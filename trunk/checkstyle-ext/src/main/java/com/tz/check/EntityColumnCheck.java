package com.tz.check;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * 实体类Column注解 Check 类
 * 只要属于@Entity注解的实体类，其持久化属性的get方法上方必须加@Column
 *
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/30 13:16
 */
public class EntityColumnCheck extends Check {

    private DetailAST columnAST;

    @Override
    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF};
    }

    @Override
    public void beginTree(DetailAST aRootAST) {
        super.beginTree(aRootAST);
        columnAST = null;
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (beginWithGet(ast)
                && isEntityClass(ast)
                && notSpecialCase(ast)) {
            if (noAnnotationColumn(ast)) {
                String message = "@Column or @JoinXxx or @JoinTable is required";
                log(ast.getLineNo(), message);
            } else {
                if (noAttrName(columnAST)) {
                    String message = "name attribute is required";
                    log(ast.getLineNo(), message);
                }
                /*else{
                    //todo...指定数据库的关键字检查
                    //根据配置文件初始化,迭代排除或正则匹配...
                }*/
            }
        }
    }

    /**
     * 虽然加了@Column,但没有定义name属性
     *
     * @param ast DetailAST
     * @return 如果没有name属性，返回true；如果有name属性,返回false.
     */
    private boolean noAttrName(DetailAST ast) {
        for (DetailAST i = ast.getFirstChild(); i != null; i = i.getNextSibling()) {
            if (i.getType() == TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR) {
                String name = i.findFirstToken(TokenTypes.IDENT).getText();
                if ("name".equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 方法没有加@Column注解
     *
     * @param ast DetailAST
     * @return 如果没有@Column返回true；如果有@Column返回false
     */
    private boolean noAnnotationColumn(DetailAST ast) {
        DetailAST modifiersAST = ast.findFirstToken(TokenTypes.MODIFIERS);
        for (DetailAST i = modifiersAST.getFirstChild(); i != null; i = i.getNextSibling()) {
            if (i.getType() == TokenTypes.ANNOTATION) {
                String name = i.findFirstToken(TokenTypes.IDENT).getText();
                if ("Column".equals(name)) {
                    columnAST = i; //用来做后续的name属性判断
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 方法没有 @Transient，即不是瞬态的，
     * 方法没有 @XxxToXxx，即非关联关系
     * 以上特例，规范检查时需要加以排除。
     *
     * @param ast DetailAST
     * @return 如果发现有，则返回false;如果没发现，则返回true
     */
    private boolean notSpecialCase(DetailAST ast) {
        DetailAST modifiersAST = ast.findFirstToken(TokenTypes.MODIFIERS);
        for (DetailAST i = modifiersAST.getFirstChild(); i != null; i = i.getNextSibling()) {
            if (i.getType() == TokenTypes.ANNOTATION) {
                String name = i.findFirstToken(TokenTypes.IDENT).getText();
                if ("Transient".equals(name) || name.indexOf("To") > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 方法以get开头
     *
     * @param ast DetailAST
     * @return 如果发现以get开头，返回true；反之返回false.
     */
    private boolean beginWithGet(DetailAST ast) {
        DetailAST identifier = ast.findFirstToken(TokenTypes.IDENT);
        return identifier.getText().startsWith("get");
    }

    /**
     * 所属类是一个实体类
     * 判断逻辑:
     * 1)存在@Entity （<===目前采用）
     * 或2)继承BaseEntity
     *
     * @param ast DetailAST
     * @return 发现@Entity,返回true；反之返回false.
     */
    private boolean isEntityClass(DetailAST ast) {
        if (ast.getParent().getType() == TokenTypes.OBJBLOCK) {
            DetailAST classAST = ast.getParent().getParent();
            DetailAST modifiersAST = classAST.findFirstToken(TokenTypes.MODIFIERS);
            for (DetailAST i = modifiersAST.getFirstChild(); i != null; i = i.getNextSibling()) {
                if (i.getType() == TokenTypes.ANNOTATION) {
                    //对于集合类型注解，加以非空判断
                    if(i.findFirstToken(TokenTypes.IDENT) != null){
                        String name = i.findFirstToken(TokenTypes.IDENT).getText();
                        if ("Entity".equals(name)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


}
