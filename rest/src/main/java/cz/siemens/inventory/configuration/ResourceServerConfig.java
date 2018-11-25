package cz.siemens.inventory.configuration;

import cz.siemens.inventory.controllers.ApiUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final ResourceServerTokenServices tokenServices;

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	@Value("${security.signing-key}")
	private String signingKey;

	@Autowired
	public ResourceServerConfig(ResourceServerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceIds).tokenServices(tokenServices);
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(signingKey);
		return converter;
	}

	@Bean
	protected OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
		return new OAuth2AccessDeniedHandler();
	}

	@Bean
	protected OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
		return new OAuth2AuthenticationEntryPoint();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.requestMatchers()
				.and()
				.exceptionHandling().accessDeniedHandler(oauthAccessDeniedHandler())
				.authenticationEntryPoint(oauthAuthenticationEntryPoint())
				.and()
				.authorizeRequests()
				.antMatchers("/actuator/**", "/index.html", "/documentation/**").permitAll()
				.mvcMatchers(GET,
						ApiUris.COMPANY_OWNERS_URI + "/**",
						ApiUris.DEPARTMENT_URI + "/**",
						ApiUris.DEVICES_URI + "/**",
						ApiUris.DEVICE_STATES_URI + "/**",
						ApiUris.DEVICE_TYPES_URI + "/**",
						ApiUris.PROJECTS_URI + "/**",
						ApiUris.SUPPLIERS_URI + "/**",
						ApiUris.USERS_URI + "/**"
						).hasRole("READ")
				.mvcMatchers(POST,
						ApiUris.COMPANY_OWNERS_URI + "/**",
						ApiUris.DEPARTMENT_URI + "/**",
						ApiUris.DEVICES_URI + "/**",
						ApiUris.DEVICE_STATES_URI + "/**",
						ApiUris.DEVICE_TYPES_URI + "/**",
						ApiUris.PROJECTS_URI + "/**",
						ApiUris.SUPPLIERS_URI + "/**").hasRole("WRITE")
				.mvcMatchers(PUT,
						ApiUris.COMPANY_OWNERS_URI + "/**",
						ApiUris.DEPARTMENT_URI + "/**",
						ApiUris.DEVICES_URI + "/**",
						ApiUris.DEVICE_STATES_URI + "/**",
						ApiUris.DEVICE_TYPES_URI + "/**",
						ApiUris.PROJECTS_URI + "/**",
						ApiUris.SUPPLIERS_URI + "/**").hasRole("WRITE")
				.mvcMatchers(DELETE,
						ApiUris.COMPANY_OWNERS_URI + "/**",
						ApiUris.DEPARTMENT_URI + "/**",
						ApiUris.DEVICES_URI + "/**",
						ApiUris.DEVICE_STATES_URI + "/**",
						ApiUris.DEVICE_TYPES_URI + "/**",
						ApiUris.PROJECTS_URI + "/**",
						ApiUris.SUPPLIERS_URI + "/**").hasRole("WRITE")

				//Inventory
				.mvcMatchers(GET, ApiUris.INVENTORY_URI + "/**").hasRole("INVENTORY")
				.mvcMatchers(PUT, ApiUris.INVENTORY_URI + "/**").hasRole("INVENTORY")

				//Calibration
				.mvcMatchers(GET, ApiUris.CALIBRATIONS_URI + "/**").hasRole("REVISION")
				.mvcMatchers(PUT, ApiUris.CALIBRATIONS_URI + "/**").hasRole("REVISION")

				//Electric Revision
				.mvcMatchers(GET, ApiUris.EL_REVISION_URI + "/**").hasRole("REVISION")
				.mvcMatchers(PUT, ApiUris.EL_REVISION_URI + "/**").hasRole("REVISION")

				//Borrow
				.mvcMatchers(POST, ApiUris.BORROW_URI + "/**").hasRole("BORROW")

				.anyRequest().denyAll();
	}
}
