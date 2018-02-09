package com.gbconnect.groupby.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence")
    private Long id;
    private String authority;
}
