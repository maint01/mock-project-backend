package com.itsol.train.mock.rest.vm;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginVM {
    @NotNull
    @Size(min = 5, max = 35)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String login;
    @NotNull
    private String password;
    private Boolean rememberMe;
}
