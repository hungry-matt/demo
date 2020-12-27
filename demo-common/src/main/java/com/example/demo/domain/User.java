package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @NotEmpty
    private String name;

    @Setter
    @NotEmpty
    private String email;

    private String password;

    @Setter
    @NotNull
    private Long level;

    public boolean isAdmin() {
        return level >= 100;
    }

    public boolean isActive() {
        return level > 0;
    }

    public void deactivate() {
        this.level = 0L;
    }

    @JsonIgnore
    //json으로 결과를 리턴할때 accessToken을 일괄적으로 전달하지 않기.
    //user 정보 조회시 password 데이터가 없으면 NPE 발생.
    public String getAccessToken() {
        return password == null ? "" : password.substring(0, 10);
    }
}
