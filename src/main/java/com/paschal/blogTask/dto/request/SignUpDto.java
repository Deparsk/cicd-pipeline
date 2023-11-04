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
public class SignUpDto {

    @NotBlank(message = "required")
    @Size(min = 2, max = 25)
    private String username;

    @NotBlank(message = "required")
    @Size(min = 10, max = 25)
    @Email(message = "put correct email")
    private String email;

    @NotBlank(message = "required")
    @Size(min = 8, max = 15)
    private String password;

    @NotBlank(message = "required")
    @Size(min = 8, max = 15)
    private String confirmPassword;

}
