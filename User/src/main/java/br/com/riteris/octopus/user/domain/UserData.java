package br.com.riteris.octopus.user.domain;

import br.com.riteris.octopus.utils.CollectionAndMapTools;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

public class UserData implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = -8659642380081083243L;

    private final String username;

    private String password;

    private Set< GrantedAuthority > authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    private String fullName;

    private String emailAddress;

    private String contactNumber;

    private String userGroup;

    private String groupRole;

    public UserData( final String username, final String password, final Set< GrantedAuthority > authorities ) {
        this( username, password, true, true, true, true, authorities, null, null, null, null, null );
    }

    public UserData( final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
                     final boolean accountNonLocked, final Set< GrantedAuthority > authorities, final String fullName, final String emailAddress,
                     final String contactNumber, final String userGroup, final String groupRole ) {
        if ( stringIsNullOrEmptyOrBlank( username ) ) {
            throw new IllegalArgumentException( "The username can' be null, empty or blank." );
        }

        if ( stringIsNullOrEmptyOrBlank( password ) ) {
            throw new IllegalArgumentException( "The user password can' be null, empty or blank.\" );" );
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + ( ( username == null ) ? 0 : username.hashCode() );

        return result;
    }

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
        } else if ( !username.equals( other.username ) )
            return false;

        return true;
    }

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
        } else {
            sb.append( "Not granted any authorities" );
        }

        sb.append( "FullName: " ).append( this.fullName ).append( "; " );
        sb.append( "EmailAddress: " ).append( this.emailAddress ).append( "; " );
        sb.append( "ContactNumber: " ).append( this.contactNumber ).append( "; " );
        sb.append( "UserGroup: " ).append( this.userGroup ).append( "; " );
        sb.append( "GroupRole: " ).append( this.groupRole );

        return sb.toString();
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    @Override
    public Collection< ? extends GrantedAuthority > getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}