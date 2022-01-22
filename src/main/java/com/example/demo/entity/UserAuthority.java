package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_authority")
@IdClass(UserAuthority.class)
public class UserAuthority implements GrantedAuthority {

    @Id
    @Column(name="user_id")
    private Long userId;

    private String authority;
}
