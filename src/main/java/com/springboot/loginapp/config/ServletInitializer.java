package com.springboot.loginapp.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <P>
 * This takes care of all the Servlet configuration with mapping URLs with
 * Controller Classes.
 * </P>
 * 
 * @author munirathinavel
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoginappApplication.class);
	}

}
