package ru.nova.clientnovabook.webClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;
import ru.nova.clientnovabook.model.Comment;
import ru.nova.clientnovabook.model.Post;

import java.util.List;

@HttpExchange("http://127.0.0.1:8091/post")
public interface WallWebClient {
    @GetExchange()
    List<Post> getPosts();
    @GetExchange("/{id}")
    Post getPostById(@PathVariable long id);
    @GetExchange()
    List<Post> getPostsByOwnerId(@RequestParam("ownerId") long ownerId,
                                 @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "25") int pageSize,
                                 @RequestParam(required = false, defaultValue = "DESC") String direction,
                                 @RequestParam(required = false, defaultValue = "dateCreation") String sortByField);
    @GetExchange("/cnt")
    Integer getCountPostsByOwnerId(@RequestParam("ownerId") long ownerId);
    @PostExchange()
    Post createPost(@RequestBody() Post post);
    @PutExchange()
    Post editPost(@RequestBody() Post post);
    @DeleteExchange("/{id}")
    long deletePost(@PathVariable long id);
    @PostExchange("/{postId}/comment")
    Comment addComment(@PathVariable long postId,
                       @RequestBody Comment comment);
}
