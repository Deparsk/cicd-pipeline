package com.paschal.blogTask.dto.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDto {
    private Long AdminId;
    private String username;
    private String email;
    private String role;
}