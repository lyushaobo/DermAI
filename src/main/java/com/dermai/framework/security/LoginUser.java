package com.dermai.framework.security;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dermai.project.system.domain.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * @author Shaobo
 */
@Data
public class LoginUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * User ID
     */
    private Long userId;

    /**
     * Dept ID
     */
    private Long deptId;

    /**
     * token
     */
    private String token;

    /**
     * Login Time
     */
    private Long loginTime;

    /**
     * Expire Time
     */
    private Long expireTime;

    /**
     * Login IP
     */
    private String ipaddr;

    /**
     * Login Location
     */
    private String loginLocation;

    /**
     * Browser Type
     */
    private String browser;

    /**
     * Operating System
     */
    private String os;

    /**
     * Permissions
     */
    private Set<String> permissions;

    /**
     * User Info
     */
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
