package com.main.janBnb.payload;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.main.janBnb.entity.enums.Role;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class UserDto {

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Role is mandatory")
    private Role role;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8,message = "Password should have at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
    )
    private String password;


    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, message = "Username should have at least 3 characters")
    private String username;


    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name should be between 2 and 50 characters")
    private String name;

    private String userId;
}
