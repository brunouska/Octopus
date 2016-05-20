package br.com.riteris.octopus.utils;

import java.util.Collection;
import java.util.Map;

/**
 * Conjunto de métodos utilitários para a manipulação de coleções e mapas. Este objeto, por tratar-se de um conjunto
 * de utilitários, foi projetado para se
 * integrar de forma transparente ao código fonte fornecendo métodos utilitários, os quais foram projetados com
 * programação defensiva para não lançarem exceções
 * em seu funcionamento interno.
 *
 * @author Bruno Barauskas
 * @version 1.0.0 - Criação do objeto.
 * @since 1.0.0 - Criada em 14 de out de 2015
 */
public final class CollectionAndMapTools {

    /**
     * Construtor do objeto, com visibilidade privada, pois o objeto é um utilitário portador de métodos estáticos.
     *
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    private CollectionAndMapTools() {
    }

    /**
     * Testa qualquer objeto do tipo java.util.Collection para verificar se a coleção está nula ou vazia.
     *
     * @param collectionToEval Coleção para o teste.
     *
     * @return True caso a coleção testada esteja nula ou vazia e false caso contrário.
     *
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    public static boolean collectionIsNullOrEmpty( Collection< ? > collectionToEval ) {
        return collectionToEval == null || collectionToEval.isEmpty();
    }

    /**
     * Testa qualquer objeto do tipo java.util.Map para verificar se o mapa está nulo ou vazio.
     *
     * @param mapToEval Mapa para o teste.
     *
     * @return True caso o mapa testado esteja nulo ou vazio e false caso contrário.
     *
     * @since 1.0.0 - Criada em 14 de out de 2015
     */
    public static boolean mapIsNullOrEmpty( Map< ?, ? > mapToEval ) {
        return mapToEval == null || mapToEval.isEmpty();
    }

}