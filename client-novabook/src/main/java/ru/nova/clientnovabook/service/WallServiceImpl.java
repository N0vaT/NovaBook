package ru.nova.clientnovabook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nova.clientnovabook.model.dto.WallDto;

@Service
@RequiredArgsConstructor
public class WallServiceImpl implements WallService{
    private final PostService postService;
    @Override
    public WallDto getWallByOwnerId(long ownerId, int pageNumber, int pageSize, String direction, String sortByField) {
        return WallDto.builder()
                .allPostCount(postService.getCountPostsByOwnerId(ownerId))
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .posts(postService.findPostsByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField))
                .build();
    }
}
