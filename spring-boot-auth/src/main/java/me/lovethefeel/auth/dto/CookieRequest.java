package me.lovethefeel.auth.dto;

import javax.validation.constraints.NotBlank;

public class CookieRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public CookieRequest() {
    }

    private CookieRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public static CookieRequest of(final String email, final String password) {
        return new CookieRequest(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
