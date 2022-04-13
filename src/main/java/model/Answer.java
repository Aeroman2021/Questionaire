package model;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @Column(nullable = false)
    private Question question;

    @OneToOne
    @Column(nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer rate;



}
