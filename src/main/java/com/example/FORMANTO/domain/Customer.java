package com.example.FORMANTO.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customer_tb")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false)
    private Long customerId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_num", nullable = false, unique = true)
    private String phoneNum;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "role", nullable = false)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public Customer(String username, String password, String phoneNum, String email, String adminKey) {

        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.role = (adminKey != null && adminKey.equals("hg463jd45djf")) ? "admin" : "customer";

    }
}
