package model;

import lombok.*;

import javax.persistence.*;

@Table(name = "question")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String description;


}
