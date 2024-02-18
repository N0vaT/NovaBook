package ru.nova.userapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "nb_clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "registration_date")
    private LocalDateTime registrationDate;
    @Column(name = "avatar_name")
    private String avatarName;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "sex")
    private Sex sex;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "nb_clients_friends",
            joinColumns = @JoinColumn (name = "user_from"),
            inverseJoinColumns = @JoinColumn(name = "user_to")
    )
    private List<User> friends;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userFrom")
    private List<FriendInvite> requestFriendInvites;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userIo")
    private List<FriendInvite> responseFriendInvites;
    public enum Sex{
        NONE, WOMAN, MAN
    }

}
