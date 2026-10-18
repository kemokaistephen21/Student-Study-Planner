package com.studentstudyplanner.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    // executed upon request
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("[PRE-HANDLE] {} {} | Routing to Controller: {}#{}()", 
                    request.getMethod(),
                    request.getRequestURI(),
                    handlerMethod.getBeanType().getSimpleName(),
                    handlerMethod.getMethod().getName());
        } else {
            log.info("[PRE-HANDLE] {} {}", request.getMethod(), request.getRequestURI());
        }

        return true; 
    }

    // after controller exits but before view is rendered
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("[POST-HANDLE] Preparing response view for URI: {}", request.getRequestURI());
    }

    // after view is rendered
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long duration = System.currentTimeMillis() - startTime;

        log.info("[AFTER-COMPLETION] {} {} | Status: {} | Execution Time: {}ms", 
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(), 
                duration);

        if (ex != null) {
            log.error("[ERROR] Request failed with exception: ", ex);
        }
    }
}
