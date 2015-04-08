package com.ingesup.truckcenter.listener;

import com.ingesup.truckcenter.TruckCenterApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by lopes_f on 4/8/2015.
 * <florian.lopes@outlook.com>
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TruckCenterApplication.class);
    }
}
