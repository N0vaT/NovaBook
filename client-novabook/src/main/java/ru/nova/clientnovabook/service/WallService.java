package ru.nova.clientnovabook.service;

import ru.nova.clientnovabook.model.dto.WallDto;

public interface WallService {
    WallDto getWallByOwnerId(long ownerId, int pageNumber, int pageSize, String direction, String sortByField);
}
