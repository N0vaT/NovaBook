package ru.nova.clientnovabook.exception;

public class FileNotImageException extends RuntimeException{
    public FileNotImageException(String message) {
        super(message);
    }
}
