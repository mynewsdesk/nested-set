package no.mnd.nestedset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class NestedSetTest {

    private NestedSet nestedSet;

    @Before
    public void setup() {
        nestedSet = new NestedSet(new Fixtures().getDataSource(), new TableMeta());
    }

    @Test
    public void shouldFindTreeFromTop() {
        List<String> result = nestedSet.getTreeNodesFromTop("PORTABLE ELECTRONICS");
        Assert.assertEquals(5, result.size());

        result.forEach(System.out::println);
    }

    @Test
    public void shouldFindTree() {
        List<String> result = nestedSet.getTreeNodes();
        Assert.assertEquals(10, result.size());

        result.forEach(System.out::println);
    }


    @Test
    public void shouldFindLeafNodes() {
        List<String> result = nestedSet.getLeafNodes();

        Assert.assertEquals(6, result.size());
        Assert.assertTrue(result.contains("TUBE"));
        Assert.assertTrue(result.contains("LCD"));
        Assert.assertTrue(result.contains("PLASMA"));
        Assert.assertTrue(result.contains("FLASH"));
        Assert.assertTrue(result.contains("CD PLAYERS"));
        Assert.assertTrue(result.contains("2 WAY RADIOS"));

        result.forEach(System.out::println);
    }


}