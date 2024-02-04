package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.Comment;
import ru.nova.clientnovabook.model.Mapper;
import ru.nova.clientnovabook.model.Post;
import ru.nova.clientnovabook.model.User;
import ru.nova.clientnovabook.model.dto.WallDto;

@Service
@RequiredArgsConstructor
public class WallServiceImpl implements WallService{
    private final PostService postService;
    private final UserService userService;
    private final Mapper mapper;
    @Override
    public WallDto getWallByOwnerId(long ownerId, int pageNumber, int pageSize, String direction, String sortByField) {
        WallDto wall = WallDto.builder()
                .allPostCount(postService.getCountPostsByOwnerId(ownerId).getPostCount())
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .posts(postService.findPostsByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField))
                .build();
        wall.getPosts().stream()
                .flatMap(x->x.getComments().stream())
                .forEach(this::addUserInfoInComment);
        return wall;
    }
    public void addUserInfoInComment(Comment comment){
        User user = userService.findUserById(comment.getOwnerId());
        comment.setName(mapper.getFullName(user));
        comment.setAvatarName(mapper.getAvatarName(user));
    }
}
