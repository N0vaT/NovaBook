package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.config.UserWebClient;
import ru.nova.clientnovabook.model.User;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestUserService implements UserService{
//    private RestTemplate restTemplate;
    private final UserWebClient userWebClient;

//    public RestUserService(String accessToken) {
////        this.restTemplate = new RestTemplate();
////        if (accessToken != null) {
////            this.restTemplate
////                    .getInterceptors()
////                    .add(getBearerTokenInterceptor(accessToken));
////        }
//    }


    @Override
    public List<User> findUsers() {
        return userWebClient.getUsers();
    }
    @Override
    public User findUserById(long userId) {
        return userWebClient.getUserById(userId);
    }
    @Override
    public User findUserByAuthorityId(long userAuthorityId) {
        return userWebClient.findUserByAuthorityId(userAuthorityId);
    }

//    @Override
//    public User findUserByName(String username) {
//        System.out.println("Im this");
////        String str = welcomeClient.getWelcome();
////        System.out.println(str);
////        User u = restTemplate.getForObject(
////                "http://127.0.0.1:8090/demo", User.class);
//        return userWebClient.findUserByName(username);
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        return userWebClient.;
//    }

    //    private ClientHttpRequestInterceptor
//    getBearerTokenInterceptor(String accessToken) {
//        ClientHttpRequestInterceptor interceptor =
//                new ClientHttpRequestInterceptor() {
//                    @Override
//                    public ClientHttpResponse intercept(
//                            HttpRequest request, byte[] bytes,
//                            ClientHttpRequestExecution execution) throws IOException {
//                        request.getHeaders().add("Authorization", "Bearer " + accessToken);
//                        return execution.execute(request, bytes);
//                    }
//                };
//
//        return interceptor;
//    }
}
