package com.itsol.train.mock.rest;

import com.itsol.train.mock.dto.UserDto;
import com.itsol.train.mock.rest.vm.LoginVM;
import com.itsol.train.mock.security.jwt.JWTFilter;
import com.itsol.train.mock.security.jwt.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api")
public class UserJWTController {
    private Logger log = LoggerFactory.getLogger(getClass());

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;

    public UserJWTController(AuthenticationManagerBuilder authenticationManagerBuilder,
                             TokenProvider tokenProvider) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Void> authorize(@Valid @RequestBody LoginVM loginVM) {
        log.trace("REST request to authenticate user: {}", loginVM);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginVM.getLogin(), loginVM.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = loginVM.getRememberMe() == null ? false : loginVM.getRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s",  jwt));
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
