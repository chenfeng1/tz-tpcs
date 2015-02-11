package com.tz.tpcs.web;

import com.tz.tpcs.service.security.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目全局错误处理 Controller，
 * 期间可以初始化在错误页面中显示的动态数据，
 * 或加入额外的业务逻辑。
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/5 20:34
 */
@ControllerAdvice
public class GlobalExceptionController {

    /**
     * 处理 404 错误
     * @param request HttpServletRequest
     * @param model Model
     * @return 错误页面
     */
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundHandler(HttpServletRequest request, Model model) {
        String uri = request.getRequestURI();
        model.addAttribute("status", HttpStatus.NOT_FOUND);
        model.addAttribute("uri", uri);
        return "forward:/WEB-INF/jsp/error.jsp";
    }

    /**
     * exceptionHandler
     * @param e Exception
     * @return String
     */
    /*@ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        return "Exception:"+e.getMessage();
    }*/

}
