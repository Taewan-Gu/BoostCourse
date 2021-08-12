package com.ntscorp.intern.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = {"com.ntscorp.intern"}, useDefaultFilters = true, excludeFilters = {
	@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, Configuration.class})
})
@PropertySource("classpath:application.properties")
@Import({DbConfiguration.class})
public class ApplicationConfiguration {

}