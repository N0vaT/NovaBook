package ru.nova.userapi.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "nb_clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"userId"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
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
    @JsonIgnore
    private List<User> friends;
    @OneToMany(mappedBy = "userFrom")
//    @JsonManagedReference(value = "userFrom")
    @JsonIgnore
    private List<FriendInvite> requestFriendInvites;
    @OneToMany(mappedBy = "userTo")
//    @JsonManagedReference(value = "userTo")
    @JsonIgnore
    private List<FriendInvite> responseFriendInvites;
    public enum Sex{
        NONE, WOMAN, MAN
    }
}
