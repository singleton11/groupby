package com.gbconnect.groupby.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;
    private String authority;
}
