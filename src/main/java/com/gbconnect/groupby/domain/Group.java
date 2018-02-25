package com.gbconnect.groupby.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gbconnect.groupby.enums.StopRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class Group {
    @Id
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Calendar created;

    private Integer numberOfUsers;

    /**
     * Amount of each user should pay
     */
    @NotNull
    private Long amount;

    /**
     * Time when group should finish to gather users
     */
    private Calendar timeToFinish;

    /**
     * Rule to spot users gathering
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private StopRule stopRule;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")}
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<User> users;

    @PrePersist
    public void onPrePersist() {
        owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
