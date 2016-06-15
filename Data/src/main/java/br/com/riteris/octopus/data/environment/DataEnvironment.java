package br.com.riteris.octopus.data.environment;

import static br.com.riteris.octopus.utils.StringTools.stringIsNullOrEmptyOrBlank;

/**
 * Enum contendo os valores possíveis para os ambientes de dados dos repositórios.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do ENUM.
 * @since 1.0.0 - Criada em 6 de mar de 2016
 */
public enum DataEnvironment {

    PD( "Production", "ROLE_ACCESS_PD" ),
    HL( "Homologation", "ROLE_ACCESS_HL" ),
    DV( "Development", "ROLE_ACCESS_DV" );

    /**
     * Descrição do ambiente de dados.
     */
    private final String description;

    /**
     * Descrição da regra de acesso necessária para utilizar o ambiente de dados.
     */
    private final String accessRole;

    /**
     * Construtor do objeto.
     *
     * @param description {@link #description}.
     * @param accessRole  {@link #accessRole}.
     *
     * @since 1.0.0 - Criada em 6 de mar de 2016
     */
    DataEnvironment( final String description, final String accessRole ) {
        this.description = description;
        this.accessRole = accessRole;
    }

    /**
     * Fornece uma descrição detalhada do ambiente de dados, contendo a sua sigla e a sua respectva descrição.
     *
     * @return Descrição detalhada do ambiente de dados e a sua respectiva descrição.
     *
     * @since 1.0.0 - Criada em 6 de mar de 2016
     */
    public final String getCompleteName() {
        return ( this.name() + "-" + this.description );
    }

    /**
     * Obtém o ambiente de dados correspondente à regra de acesso informada. Caso a regra de acesso não seja informada
     * corretamente ou não existe nenhum
     * ambiente de dados correspondente à regra de acesso será retornado nulo.
     *
     * @param accessRole Regra de acesso à qual desejamos obter o ambiente de dados correspondente.
     *
     * @return Ambiente de dados correspondente à regra de acesso informada ou nulo caso a regra de acesso não seja
     * informada corretamente ou não exista um
     * ambiente de dados correspondente.
     *
     * @since 1.0.0 - Criada em 6 de mar de 2016
     */
    public final DataEnvironment getEnvironmentByAccessRole( final String accessRole ) {
        if ( stringIsNullOrEmptyOrBlank( accessRole ) ) {
            return null;
        }

        for ( DataEnvironment dataEnvironment : DataEnvironment.values() ) {
            if ( dataEnvironment.accessRole.equals( accessRole ) ) {
                return dataEnvironment;
            }
        }

        return null;
    }

}
