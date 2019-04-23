package com.mercan.zuul.filters;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;
import com.mercan.zuul.utils.FilterUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TrackingFilter extends ZuulFilter {
    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER=true;


    private final FilterUtils filterUtils;

    @Override
    public String filterType() {
        return FilterUtils.FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        if(filterUtils.isCorrelationIdPresent()){
            log.debug("corre id is {}" , filterUtils.getCorrelationId());
        }else{
            filterUtils.setCorrelationId(generateCorrelationId());
            log.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("Processing incoming request for {}.", ctx.getRequest().getRequestURI());
        return null;

    }

    private String generateCorrelationId(){
        return java.util.UUID.randomUUID().toString();
    }

}
