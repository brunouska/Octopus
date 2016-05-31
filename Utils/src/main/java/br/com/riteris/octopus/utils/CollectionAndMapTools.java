package br.com.riteris.octopus.utils;

import java.util.Collection;
import java.util.Map;

public final class CollectionAndMapTools {

    private CollectionAndMapTools() {
    }

    public static boolean collectionIsNullOrEmpty( Collection< ? > collectionToEval ) {
        return collectionToEval == null || collectionToEval.isEmpty();
    }

    public static boolean mapIsNullOrEmpty( Map< ?, ? > mapToEval ) {
        return mapToEval == null || mapToEval.isEmpty();
    }

}