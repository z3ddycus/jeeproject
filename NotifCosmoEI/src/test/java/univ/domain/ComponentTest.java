package univ.domain;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;


public class ComponentTest {
    @Test
    public void getInheritanceList() throws Exception {
        Component ggfather = new Component("ggfather");
        Component gfather = new Component("gfather", ggfather);
        Component father = new Component("father", gfather);
        Component child = new Component("son", father);
        LinkedList<Component> result = new LinkedList<>();
        result.addFirst(child);
        result.addFirst(father);
        result.addFirst(gfather);
        result.addFirst(ggfather);
        assertEquals(child.getInheritanceList(), result);
    }

}