package ir.malakouti.questionaire.model.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customrs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @OneToOne(mappedBy = "customer")
    private AnswerEntity answer;

    @Column(name = "personal_character_number",nullable = false)
    private Long personalCharacterNumber;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity)) return false;
        CustomerEntity user = (CustomerEntity) o;
        return Objects.equals(id, user.id) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                personalCharacterNumber.equals(user.personalCharacterNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, username, password,personalCharacterNumber);
    }
}
