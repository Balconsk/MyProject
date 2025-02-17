package ru.balcon.MyProject.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.balcon.MyProject.filter.SimpleCORSFilter;

public class SpringDispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer{
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
        registerEncodingFilter(aServletContext);
        registerSimpleCORSFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
    private void registerEncodingFilter(ServletContext aContext) {
        aContext.addFilter("encodingFilter",
                new CharacterEncodingFilter("UTF-8",true));
//                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
    private void registerSimpleCORSFilter(ServletContext aContext){
        aContext.addFilter("simpleCORSFilter", new SimpleCORSFilter());
    }
}

