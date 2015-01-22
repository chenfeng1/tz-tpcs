package com.tz.tpcs.service.security;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.List;

/**
 * 自定义url授权逻辑
 * Created by Hu Jing Ling on 2015/1/22.
 */
public class CustomAccessDecisionManager extends AbstractAccessDecisionManager {

    private static final Logger logger = Logger.getLogger(CustomAccessDecisionManager.class);

    public CustomAccessDecisionManager(List<AccessDecisionVoter> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object filter, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.debug("decide() run...");
        //判断 filter 非空
        if ((filter == null) || !this.supports(filter.getClass())) {
            throw new IllegalArgumentException("Object must be a FilterInvocation");
        }

        String url = ((FilterInvocation) filter).getRequestUrl();
        logger.trace("url:" + url);

        int deny = 0;
        for (AccessDecisionVoter voter : getDecisionVoters()) {
            deny = voter.vote(authentication, filter, configAttributes);
        }
        //根据 deny 取值进行判断
        if (deny == AccessDecisionVoter.ACCESS_GRANTED) {
            //判断当前用户是否获得此URL资源的授权
            if (true) {
                logger.debug("获得授权 URL:" + url);
            }
        } else if (deny < 0 || !isAllowIfAllAbstainDecisions()){
            //未获得授权，URL 不放行
            logger.debug("拒绝授权 URL:" + url);
            throw new AccessDeniedException(messages.getMessage("AbstractAccessDecisionManager.accessDenied",
                    "Access is denied"));
        }
    }
}
