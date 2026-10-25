
package com.studentstudyplanner.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configures Spring MVC settings for the Student Study Planner application.
 *
 * This class registers the LoggingInterceptor so HTTP requests
 * to selected application pages can be tracked and logged.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Logging interceptor used to track HTTP requests.
     */
     private final LoggingInterceptor loggingInterceptor;

    /**
     * Creates the WebMvcConfig using the provided logging interceptor.
     *
     * Spring provides the interceptor through constructor injection.
     *
     * @param loggingInterceptor the interceptor used to log HTTP requests
     */
    public WebMvcConfig(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    /**
     * Registers the logging interceptor with Spring MVC.
     *
     * The interceptor is applied to the application's home page,
     * authentication pages, courses, assignments, tasks, and notes.
     * It also applies to matching paths within those sections.
     *
     * @param registry the interceptor registry used to register
     *                 interceptors and their URL patterns
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/", "/register", "/login", "/logout",
                        "/courses", "/courses/**", "/assignments", "/assignments/**",
                        "/tasks", "/tasks/**", "/notes", "/notes/**");
    }
}
