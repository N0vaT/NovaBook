package ru.nova.clientnovabook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.clientnovabook.model.Post;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WallDto {
    private int allPostCount;
    private int pageNumber;
    private int pageSize;
    private List<Post> posts;

    public int getPageCount(){
        return (int) Math.ceil(1.0 * allPostCount / pageSize) - 1;
    }
}
