package com.ingesup.truckcenter.security;


import com.ingesup.truckcenter.TruckCenterApplication;
import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.model.User;
import com.ingesup.truckcenter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TruckCenterApplication.class})
@Sql(scripts = "/sql/test-data.sql" )
@WebAppConfiguration
public class SecurityTests {

    private static final String LOGIN_URL = "http://localhost:8080/secure/login";

    private static final String DRIVERS_URL = "/drivers";
    private static final String TRUCKS_URL = "/trucks";

	@Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Autowired
    private UserService userService;

    private UserDetails admin;
    private UserDetails driver;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        this.admin = getAdmin();
        this.driver = getDriver();
    }

    @Test
    public void driversPageShouldRequireLogin() throws Exception {
        mvc.perform(get(DRIVERS_URL))
                .andExpect(loginPage());
    }

    @Test
    public void trucksPageShouldRequireLogin() throws Exception {
        mvc.perform(get(TRUCKS_URL))
                .andExpect(loginPage());
    }

    @Test
    public void driversPageShouldRequireAdminRole() throws Exception {
        RequestBuilder request = get(DRIVERS_URL)
                .with(user(driver));

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void driversPageShouldBeAvailableForAdmin() throws Exception {
		RequestBuilder request = get(DRIVERS_URL);

		mvc.perform(request).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    private static ResultMatcher loginPage() {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                MockMvcResultMatchers.status().is3xxRedirection().match(mvcResult);
                MockMvcResultMatchers.redirectedUrl(LOGIN_URL).match(mvcResult);
            }
        };
    }

    private UserDetails getAdmin() {
        return new org.springframework.security.core.userdetails.User("admin@admin.fr", "admin", AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }

    private UserDetails getDriver() {
        return new org.springframework.security.core.userdetails.User("driver@driver.fr", "driver", AuthorityUtils.createAuthorityList("ROLE_DRIVER"));
    }
}
