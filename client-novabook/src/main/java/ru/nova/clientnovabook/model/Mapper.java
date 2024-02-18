package ru.nova.clientnovabook.model;

import org.springframework.stereotype.Component;
import ru.nova.clientnovabook.model.dto.UserDto;

@Component
public class Mapper {

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getUserId())
                .name(getFullName(user))
                .phone(user.getPhone())
                .email(user.getEmail())
                .birthday(user.getBirthday())
                .registrationDate(user.getRegistrationDate())
                .avatarName(getAvatarName(user))
                .build();
    }

    private String getFullName(User user){
        StringBuffer name = new StringBuffer();
        if(user.getLastName() != null){
            name.append(user.getLastName());
        }
        if(user.getFirstName() != null){
            if(!name.isEmpty()){
                name.append(' ');
            }
            name.append(user.getFirstName());
        }
        if(user.getPatronymic() != null){
            if(!name.isEmpty()){
                name.append(' ');
            }
            name.append(user.getPatronymic());
        }
        if(name.isEmpty()){
            name.append("Без имени");
        }
        return name.toString();
    }

    private String getAvatarName(User user){
        if(user.getAvatarName() != null){
            return user.getAvatarName();
        }
        if(user.getSex() == null){
            return "default.png";
        }else if(user.getSex().equals(User.Sex.MAN)){
            return "default_m.jpeg";
        }else{
            return "default_w.jpeg";
        }
    }
}
