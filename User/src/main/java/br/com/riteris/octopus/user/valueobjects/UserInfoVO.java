package br.com.riteris.octopus.user.valueobjects;

/**
 * Descreve um value object que contém informações básicas a respeito do usuário.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do value object.
 * @since 1.0.0 - Criada em 18 de abr de 2016
 */
public class UserInfoVO {

    /**
     * Identificador único do usuário.
     */
    private final String username;

    /**
     * Código de localização do usuário.
     */
    private final String localeCode;

    /**
     * Construtor do objeto.
     *
     * @param username   {@link #username}.
     * @param localeCode {@link #localeCode}.
     *
     * @since 1.0.0 - Criada em 18 de abr de 2016
     */
    public UserInfoVO( String username, String localeCode ) {
        this.username = username;
        this.localeCode = localeCode;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #username}.<br>
     *
     * @return Valor atual de {@link #username}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retorna o valor atual do parâmetro {@link #localeCode}.<br>
     *
     * @return Valor atual de {@link #localeCode}
     */
    public String getLocaleCode() {
        return this.localeCode;
    }

}
