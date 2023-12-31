package com.paschal.blogTask.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String comment;
    private LocalDateTime commentDate;
    private Long postId;
    private Long userId;
}
