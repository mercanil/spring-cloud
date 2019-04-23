package com.mercan.zuul.utils;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class FilterUtils {
    private static final String CORRELATION_ID = "correlationID";
    public static final String FILTER_TYPE= "pre-filter";

    public String getCorrelationId(){
        RequestContext ctx = RequestContext.getCurrentContext();
        String corrId = ctx.getRequest().getHeader(CORRELATION_ID);
        if(corrId != null){
            return corrId;
        }else{
            return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId){
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);

    }

    public boolean isCorrelationIdPresent() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String corrId = ctx.getRequest().getHeader(CORRELATION_ID);
        if(corrId != null){
            return false;
        }else{
            return true;
        }


    }
}
