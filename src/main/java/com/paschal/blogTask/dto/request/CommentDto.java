package com.paschal.blogTask.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String comment;
}
