package br.com.riteris.octopus.user.valueobjects;

public class UserInfoVO {

    private final String username;

    private final String localeCode;

    public UserInfoVO( final String username, final String localeCode ) {
        this.username = username;
        this.localeCode = localeCode;
    }

    public String getUsername() {
        return this.username;
    }

    public String getLocaleCode() {
        return this.localeCode;
    }

}
