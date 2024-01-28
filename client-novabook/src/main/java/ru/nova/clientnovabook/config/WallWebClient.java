package ru.nova.clientnovabook.config;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.User;

import java.util.List;

@HttpExchange("http://127.0.0.1:8091/post")
public interface WallWebClient {
//    @GetExchange()
//    List<Post> getPosts();
    @GetExchange("/{id}")
    Post getPostById(@PathVariable long id);
    @GetExchange()
    List<Post> getPostsByOwnerId(@RequestParam("ownerId") long ownerId);
    @PostExchange()
    Post createPost(@RequestBody() Post post);
    @PutExchange("/")
    Post editPost(@RequestBody() Post post);
}
