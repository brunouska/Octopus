package br.com.riteris.octopus.user.domain;

import br.com.riteris.octopus.utils.CollectionAndMapTools;
import br.com.riteris.octopus.utils.StringTools;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Objeto que contém todos os dados referentes a um usuário em particular.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 6 de mar de 2016
 */
public class UserData implements UserDetails, CredentialsContainer {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -8659642380081083243L;

    /**
     * Identificador único do usuário.
     */
    private final String username;

    /**
     * Senha do usuário.
     */
    private String password;

    /**
     * Conjunto de permissões de acesso que o usuário possui.
     */
    private Set< GrantedAuthority > authorities;

    /**
     * Flag que indica se a conta do usuário está expirada.
     */
    private boolean accountNonExpired;

    /**
     * Flag que indica que a conta do usuário não está bloqueada.
     */
    private boolean accountNonLocked;

    /**
     * Flag que indica se as credenciais do usuário estão expiradas.
     */
    private boolean credentialsNonExpired;

    /**
     * Flag que indica se a conta do usuário está ativa ou não.
     */
    private boolean enabled;

    /**
     * Nome completo do usuário.
     */
    private String fullName;

    /**
     * Endereço de e-mail do usuário.
     */
    private String emailAddress;

    /**
     * Número de telefone para contato com o usuário.
     */
    private String contactNumber;

    /**
     * Nome do grupo ao qual o usuário pertence (ex.: Dept. de Compras / Usuários Externos / Vendas).
     */
    private String userGroup;

    /**
     * Distinção do usuário dentro do grupo ao qual pertence (ex.: Gerente / Técnico / Supervisor).
     */
    private String groupRole;

    /**
     * Construtor simplificado do objeto, o qual invoca o construtor mais completo do objeto com todas as flags de
     * ativação configuradas como {@code true}.
     *
     * @param username    {@link #username}
     * @param password    {@link #password}
     * @param authorities {@link #authorities}
     *
     * @since 1.0.0 - Criada em 12 de mar de 2016
     */
    public UserData( String username, String password, Set< GrantedAuthority > authorities ) {
        this( username, password, true, true, true, true, authorities, null, null, null, null, null );
    }

    /**
     * Construtor mais completo do objeto, onde inclusive é realizada a validação dos dados informados para {@code
     * username} e {@code password} para que não
     * sejam informados incorretamente( nulos, vazios ou em branco).
     *
     * @param username              {@link #username}
     * @param password              {@link #password}
     * @param enabled               {@link #enabled}
     * @param accountNonExpired     {@link #accountNonExpired}
     * @param credentialsNonExpired {@link #credentialsNonExpired}
     * @param accountNonLocked      {@link #accountNonLocked}
     * @param authorities           {@link #authorities}
     * @param fullName              {@link #fullName}
     * @param emailAddress          {@link #emailAddress}
     * @param contactNumber         {@link #contactNumber}
     * @param userGroup             {@link #userGroup}
     * @param groupRole             {@link #groupRole}
     *
     * @since 0.0.0 - Criada em 12 de mar de 2016
     */
    public UserData( String username, String password, boolean enabled, boolean accountNonExpired, boolean
            credentialsNonExpired, boolean accountNonLocked,
                     Set< GrantedAuthority > authorities, String fullName, String emailAddress, String contactNumber,
                     String userGroup, String groupRole ) {
        if ( StringTools.isNullEmptyOrBlank( username ) || StringTools.isNullEmptyOrBlank( password ) ) {
            throw new IllegalArgumentException( "Cannot pass null or empty values to for username and password." );
        }

        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = authorities;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.userGroup = userGroup;
        this.groupRole = groupRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ( ( username == null ) ? 0 : username.hashCode() );

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;

        if ( obj == null )
            return false;

        if ( getClass() != obj.getClass() )
            return false;

        UserData other = ( UserData ) obj;

        if ( username == null ) {
            if ( other.username != null )
                return false;
        }
        else if ( !username.equals( other.username ) )
            return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( super.toString() ).append( ": " );
        sb.append( "Username: " ).append( this.username ).append( "; " );
        sb.append( "Password: [PROTECTED]; " );
        sb.append( "Enabled: " ).append( this.enabled ).append( "; " );
        sb.append( "AccountNonExpired: " ).append( this.accountNonExpired ).append( "; " );
        sb.append( "credentialsNonExpired: " ).append( this.credentialsNonExpired ).append( "; " );
        sb.append( "AccountNonLocked: " ).append( this.accountNonLocked ).append( "; " );

        if ( CollectionAndMapTools.collectionIsNullOrEmpty( authorities ) ) {
            sb.append( "Granted Authorities: " );

            boolean first = true;

            for ( GrantedAuthority auth : authorities ) {
                if ( !first ) {
                    sb.append( "," );
                }

                first = false;

                sb.append( auth );
            }
        }
        else {
            sb.append( "Not granted any authorities" );
        }

        sb.append( "FullName: " ).append( this.fullName ).append( "; " );
        sb.append( "EmailAddress: " ).append( this.emailAddress ).append( "; " );
        sb.append( "ContactNumber: " ).append( this.contactNumber ).append( "; " );
        sb.append( "UserGroup: " ).append( this.userGroup ).append( "; " );
        sb.append( "GroupRole: " ).append( this.groupRole );

        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        return this.authorities;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}