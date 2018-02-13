package com.gbconnect.groupby.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbconnect.groupby.util.validators.UniqueEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Calendar created;

    @NotNull
    @Email
    @UniqueEmail
    @JsonProperty("email")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Role> authorities;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean accountNonExpired;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean accountNonLocked;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean credentialsNonExpired;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean enabled;

    @PrePersist
    public void onPrePersist() {
        password = new BCryptPasswordEncoder().encode(password);
    }
}
