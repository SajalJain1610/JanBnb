package com.main.janBnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {
    private JwtRequestMapper jwtRequestMapper;

    public SecurityConfiguration(JwtRequestMapper jwtRequestMapper) {
        this.jwtRequestMapper = jwtRequestMapper;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestMapper, UsernamePasswordAuthenticationFilter.class);
        http.authorizeHttpRequests().requestMatchers("/api/v1/main/user/signUp",
                                                               "/api/v1/main/user/login").permitAll()
                                    .requestMatchers("/api/v1/main/country/addCountry",
                                                               "/api/v1/main/country/updCountry/{countryId}",
                                                               "/api/v1/main/country/remove/{countryId}",
                                                               "/api/v1/main/country/search",
                                                               "/api/v1/main/location/addLocation",
                                                               "/api/v1/main/location/updLocation/{locationId}",
                                                               "/api/v1/main/property/addProperty",
                                                               "/api/v1/main/property/delete/{propertyId}",
                                                               "/api/v1/main/image/upload/property/{propertyId}",
                                                               "/api/v1/main/image/delete/{imageId}")
                                    .hasRole("ADMIN")
                                    .requestMatchers("/api/v1/main/property/updProperty/{propertyId}",
                                                               "/api/v1/main/property/searchHotel",
                                                               "/api/v1/main/review/addReview/{propertyId}",
                                                               "/api/v1/main/review/updateReview/{propertyId}",
                                                               "/api/v1/main/review/deleteReview/{reviewId}",
                                                               "/api/v1/main/review/getReviewByUser",
                                                               "/api/v1/main/favourite/addFavourite/{propertyId}",
                                                               "/api/v1/main/favourite/removeFavourite/{propertyId}",
                                                               "/api/v1/main/booking/create/property/{propertyId}",
                                                                "/api/v1/main/booking/delete/{bookingId}")
                                    .hasAnyRole("ADMIN","USER").anyRequest().authenticated();
        return http.build();
    }
}
