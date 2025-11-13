package org.example.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 import java.util.ArrayList;
 import java.util.List;

@Entity
@Table(name = "chat")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "chat")
    private List<UserChat> userChats = new ArrayList<>();
}
