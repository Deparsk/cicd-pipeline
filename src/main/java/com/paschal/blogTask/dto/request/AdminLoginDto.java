package com.paschal.blogTask.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLoginDto {
    @NotBlank(message = "required")
    @Size(min = 10, max = 25)
    @Email(message = "put correct email")
    private String email;
    @NotBlank(message = "required")
    @Size(min = 8, max = 15)
    private String password;
}
