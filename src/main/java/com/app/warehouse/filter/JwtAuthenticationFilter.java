package com.app.warehouse.filter;

import com.app.warehouse.model.WMSUser;
import com.app.warehouse.utils.Commons;
import com.app.warehouse.utils.JwtUtil;
import com.app.warehouse.utils.RedisUtil;
import com.auth0.jwt.interfaces.Claim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private final RedisUtil redisUtil;

    public JwtAuthenticationFilter(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Map<String, Claim> claims = JwtUtil.getClaims(token);

        String userNumbers = claims.get("userNumbers").asString();

        WMSUser wmsUser = redisUtil.getObject(Commons.REDIS_KEY_WMS_USER + userNumbers, WMSUser.class);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(wmsUser, null, wmsUser.getAuthorities());

        log.info("权限:{}", wmsUser.getAuthorities().toString());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}
