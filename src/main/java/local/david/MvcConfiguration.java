package local.david;

import org.springframework.boot.autoconfigure.mustache.web.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by [david] on 12.03.17.
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver getViewResolver() {
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setPrefix("/resources/templates/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}