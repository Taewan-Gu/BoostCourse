package com.ntscorp.intern.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(basePackages = {"com.ntscorp.intern"}, useDefaultFilters = false, includeFilters = {
	@Filter(type = FilterType.ANNOTATION, classes = {Component.class, Repository.class, Service.class})
})
@Import({DbConfiguration.class})
public class ApplicationConfiguration {

}