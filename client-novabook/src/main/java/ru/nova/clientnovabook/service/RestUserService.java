package ru.nova.clientnovabook.service;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class RestUserService implements UserService{
    private RestTemplate restTemplate;

    public RestUserService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if (accessToken != null) {
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
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
