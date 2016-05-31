package br.com.riteris.octopus.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static br.com.riteris.octopus.utils.CollectionAndMapTools.collectionIsNullOrEmpty;
import static br.com.riteris.octopus.utils.CollectionAndMapTools.mapIsNullOrEmpty;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionAndMapToolsTest {

    @Test
    public final void testCollectionIsNullOrEmpty() {
        assertTrue( collectionIsNullOrEmpty( null ) );
        assertTrue( CollectionAndMapTools.collectionIsNullOrEmpty( new ArrayList<>() ) );

        final ArrayList< String > list = new ArrayList<>();
        list.add( "foo" );

        assertFalse( CollectionAndMapTools.collectionIsNullOrEmpty( list ) );
    }

    @Test
    public final void testMapIsNullOrEmpty() {
        assertTrue( mapIsNullOrEmpty( null ) );
        assertTrue( CollectionAndMapTools.mapIsNullOrEmpty( new HashMap<>() ) );

        final Map< String, String > map = new HashMap<>();
        map.put( "foo", "bar" );

        assertFalse( CollectionAndMapTools.mapIsNullOrEmpty( map ) );
    }

}
