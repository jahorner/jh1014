package com.toolshop.data;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.toolshop.model.Tool;

public class ToolRepositoryTests {
    ToolTypeRepository toolTypeRepository = new ToolTypeRepository();
    ToolRepository toolRepository = new ToolRepository(toolTypeRepository);

    @Test
    public void testRetrieveAll(){
        List<Tool> allTools = toolRepository.getTools();
        assertEquals(5, allTools.size());
    }

    @Test
    public void testGetOne(){
        Tool tool = toolRepository.getTool("LADW");
        assertNotNull(tool);
        assertEquals("LADW", tool.getCode());
        assertNotNull(tool.getType());
        assertEquals(1.99, tool.getType().getPrice(), 0);
    }

    @Test
    public void testNull(){
        Tool tool = toolRepository.getTool("DNE");
        assertNull(tool);
    }
    
}
