
package com.studentstudyplanner.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Logs information about HTTP requests processed by the application.
 *
 * This interceptor tracks incoming requests, controller methods,
 * response preparation, execution times, and errors.
 *
 * It implements HandlerInterceptor to perform logging before,
 * during, and after request processing.
 */
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    /**
     * Logger used to record request information and errors.
     */
    private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

    /**
     * Processes an incoming HTTP request before the controller runs.
     *
     * Records the request start time and logs the HTTP method,
     * requested URI, and controller method when available.
     *
     * @param request the incoming HTTP request
     * @param response the HTTP response
     * @param handler the handler responsible for processing the request
     * @return true to allow the request to continue processing
     * @throws Exception if an error occurs during request processing
     */
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

    /**
     * Runs after the controller finishes but before the view is rendered.
     *
     * Logs a debug message indicating that the application
     * is preparing the response view for the requested URI.
     *
     * @param request the current HTTP request
     * @param response the HTTP response
     * @param handler the handler that processed the request
     * @param modelAndView the model and view returned by the controller,
     *                     or null if no model and view is available
     * @throws Exception if an error occurs during response processing
     */
    // after controller exits but before view is rendered
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("[POST-HANDLE] Preparing response view for URI: {}", request.getRequestURI());
    }

    /**
     * Runs after the request has completed processing.
     *
     * Calculates the total request execution time and logs
     * the HTTP method, requested URI, response status,
     * and execution duration in milliseconds.
     *
     * If an exception is provided, the method also logs
     * the error information.
     *
     * @param request the completed HTTP request
     * @param response the HTTP response
     * @param handler the handler that processed the request
     * @param ex an exception raised during request processing,
     *           or null if no exception was provided
     * @throws Exception if an error occurs during completion processing
     */
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
