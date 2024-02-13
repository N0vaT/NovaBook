package ru.nova.userapi.exception;

public class FriendInviteNotFoundException extends RuntimeException{
    public FriendInviteNotFoundException(String message) {
        super(message);
    }
}
