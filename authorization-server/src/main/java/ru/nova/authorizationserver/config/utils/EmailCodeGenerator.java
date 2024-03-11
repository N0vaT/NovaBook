package ru.nova.authorizationserver.config.utils;

import java.util.concurrent.ThreadLocalRandom;

public class EmailCodeGenerator {
    public static String generateCode(){
        return String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999));
    }
}
