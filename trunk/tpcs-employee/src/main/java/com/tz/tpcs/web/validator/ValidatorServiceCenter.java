package com.tz.tpcs.web.validator;

import com.tz.tpcs.service.FieldUniqueValidatorService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/10 10:10
 */
@Component
public class ValidatorServiceCenter {

    private static final Logger LOGGER = Logger.getLogger(ValidatorServiceCenter.class);

    @Resource
    private List<FieldUniqueValidatorService> serviceList;

    /**
     * 根据指定类型，返回FieldUniqueValidatorService实例
     * @return FieldUniqueValidatorService
     */
    public FieldUniqueValidatorService getService(Class<? extends FieldUniqueValidatorService> requiredService) {
        LOGGER.debug("getService() run, get a "+requiredService);
        FieldUniqueValidatorService result = null;
        for (FieldUniqueValidatorService s : serviceList) {
            String name = s.toString();
            int pos = name.lastIndexOf(".");
            int pos2 = name.indexOf("@");
            String name2 = name.substring(pos + 1, pos2).replaceAll("Impl", "");
            String requiredName = requiredService.toString();
            if (requiredName.contains(name2)) {
                result = s;
                break;
            }
//            boolean b2 = s.getClass().isAssignableFrom(c);
//            CabinBooking.class.isAssignableFrom(object.getClass()
//            boolean b2 = s instanceof c;
//            if(s instanceof (requiredService).getClass()){
//            }
        }
        return result;
    }
}
