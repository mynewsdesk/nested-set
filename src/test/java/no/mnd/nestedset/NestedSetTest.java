package no.mnd.nestedset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
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
        List<String> expected = Arrays.asList("TUBE", "LCD", "PLASMA", "FLASH", "CD PLAYERS", "2 WAY RADIOS");
        Assert.assertEquals(expected, result);

        result.forEach(System.out::println);
    }

    @Test
    public void shouldFindDepthNodes() {
        List<ContentDepth> result = nestedSet.getDepthNodes();

        Assert.assertEquals(10, result.size());
        ContentDepth cd = result.get(5);
        Assert.assertEquals("PORTABLE ELECTRONICS", cd.content);
        Assert.assertEquals(1, cd.depth.intValue());

        result.forEach(System.out::println);

    }


}