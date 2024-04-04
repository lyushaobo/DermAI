package com.dermai.framework.security.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.dermai.common.Utils.AddressUtils;
import com.dermai.common.Utils.IpUtils;
import com.dermai.common.Utils.ServletUtils;
import com.dermai.common.constants.Constants;
import com.dermai.common.constants.RedisConstants;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import com.dermai.framework.redis.RedisCache;
import com.dermai.framework.security.LoginUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author Shaobo
 */
@Component
@Slf4j
public class TokenService {
    // jwt header
    @Value("${token.header}")
    private String header;
    // jwt secret
    @Value("${token.secret}")
    private String secret;
    // expire time
    @Value("${token.expireTime}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisCache redisCache;

    /**
     * Get Login User from request->header->auth->token->jwt claims
     *
     * @param request HttpServletRequest
     * @return LoginUser
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // Get the token carried by request_token_header
        String token = getToken(request);
        if (!StrUtil.isEmptyIfStr(token)) {
            try {
                // get jwt claims
                Claims claims = parseToken(token);
                // get uuid from claims
                String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
                // merge prefix and uuid to generate redis key
                String userKey = getTokenKey(uuid);
                // get login user
                LoginUser user = redisCache.getCacheObject(userKey);
                return user;
            } catch (Exception e) {
                log.error("Get User Information Exception'{}'", e.getMessage());
            }
        }
        return null;
    }

    /**
     * Set Login User
     */
    public void setLoginUser(LoginUser loginUser) {
        if (loginUser != null && StrUtil.isNotEmpty(loginUser.getToken())) {
            refreshToken(loginUser);
        }
    }

    /**
     * Delete Login User
     */
    public void delLoginUser(String token)
    {
        if (StrUtil.isNotEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    /**
     * Create Token
     *
     * @param loginUser Login
     * @return token
     */
    public String createToken(LoginUser loginUser) {
        String token = IdUtil.fastSimpleUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser 令牌
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }


    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddr();
        loginUser.setIpaddr(ip);
        //loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());

    }


    /**
     * Get token
     *
     * @param request HttpServletRequest
     * @return token replace "token prefix"
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        // token not empty
        if (StrUtil.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * Parse Token to Claims
     *
     * @param token token
     * @return Claims
     */
    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * merge prefix and uuid to generate redis key
     *
     * @param uuid
     * @return
     */
    private String getTokenKey(String uuid) {
        return RedisConstants.LOGIN_USER_KEY + uuid;
    }

    /**
     * refresh login user
     *
     * @param loginUser login user
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // Cache loginUser by uuid
        String userKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }
}
