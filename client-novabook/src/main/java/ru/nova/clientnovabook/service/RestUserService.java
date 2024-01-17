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
@Service
@RequiredArgsConstructor
public class RestUserService implements UserService{
//    private RestTemplate restTemplate;
    private UserWebClient userWebClient;

    public RestUserService(String accessToken) {
//        this.restTemplate = new RestTemplate();
//        if (accessToken != null) {
//            this.restTemplate
//                    .getInterceptors()
//                    .add(getBearerTokenInterceptor(accessToken));
//        }
    }

    @Override
    public User findUserByName(String username) {
        System.out.println("Im this");
//        String str = welcomeClient.getWelcome();
//        System.out.println(str);
//        User u = restTemplate.getForObject(
//                "http://127.0.0.1:8090/demo", User.class);
        return new User();
    }

    private ClientHttpRequestInterceptor
    getBearerTokenInterceptor(String accessToken) {
        ClientHttpRequestInterceptor interceptor =
                new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(
                            HttpRequest request, byte[] bytes,
                            ClientHttpRequestExecution execution) throws IOException {
                        request.getHeaders().add("Authorization", "Bearer " + accessToken);
                        System.out.println("tokkkkkke");
                        System.out.println(request.getHeaders());
                        System.out.println(request.getMethod());
                        System.out.println(request.getMethodValue());
                        System.out.println(request.getURI());
                        return execution.execute(request, bytes);
                    }
                };

        return interceptor;
    }
}
