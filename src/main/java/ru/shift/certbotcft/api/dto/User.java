package ru.shift.certbotcft.api.dto;

import java.util.List;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "license", schema = "public")
public class User {
    @Id
    private Long id;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "user_type")
    private String userType;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "license", cascade = CascadeType.ALL)
    private List<License> license;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
