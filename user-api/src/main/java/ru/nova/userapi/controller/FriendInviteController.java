package ru.nova.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.userapi.model.FriendInvite;
import ru.nova.userapi.model.dto.FriendInviteDto;
import ru.nova.userapi.service.FriendInviteService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users/{userId}/invites/friends")
@RequiredArgsConstructor
public class FriendInviteController {
    private final FriendInviteService friendInviteService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<FriendInviteDto>> getFriendInviteTo(@PathVariable long userId,
                                                                   @RequestParam(required = false, defaultValue = "all") String idParameter,
                                                                   @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                                   @RequestParam(required = false, defaultValue = "25") int pageSize,
                                                                   @RequestParam(required = false, defaultValue = "ASC") String direction,
                                                                   @RequestParam(required = false, defaultValue = "dateTime") String sortByField)
    {
        List<FriendInvite> friends = new ArrayList<>();
        if(idParameter.equals("all")){
            // TODO Change to 1 request
            friends.addAll(friendInviteService.findAllByUserFromId(userId, pageNumber, pageSize, direction, sortByField));
            friends.addAll(friendInviteService.findAllByUserToId(userId, pageNumber, pageSize, direction, sortByField));
        }else if(idParameter.equals("from")){
            friends = friendInviteService.findAllByUserFromId(userId, pageNumber, pageSize, direction, sortByField);
        }else if(idParameter.equals("to")){
            friends = friendInviteService.findAllByUserToId(userId, pageNumber, pageSize, direction, sortByField);
        }else{
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(friends.stream()
                        .map(f -> modelMapper.map(f, FriendInviteDto.class))
                .toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendInvite> saveFriendInviteTo(@PathVariable long userId,
                                                           @RequestBody FriendInviteDto friendInviteDto)
    {
        if(friendInviteDto.getStatus().equals(FriendInvite.InviteStatus.DENIED)){
            return ResponseEntity.ok(friendInviteService.save(friendInviteDto));
        }else{
            return ResponseEntity.ok(friendInviteService.save(friendInviteDto));
        }
    }
    @PutMapping("/{inviteId}")
    public ResponseEntity<FriendInvite> editUser(@PathVariable long userId,
                                                 @PathVariable long inviteId,
                                                 @RequestBody FriendInvite friendInvite){
        if(friendInvite.getStatus().equals(FriendInvite.InviteStatus.ACCEPTED)){
            return ResponseEntity.ok(friendInviteService.update(inviteId, friendInvite));
        }else if(friendInvite.getStatus().equals(FriendInvite.InviteStatus.DENIED)){
            return ResponseEntity.ok(friendInvite);
        }
        return ResponseEntity.badRequest().build();
    }
}
