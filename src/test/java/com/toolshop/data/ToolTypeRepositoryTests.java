package com.toolshop.data;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.toolshop.model.ToolType;

public class ToolTypeRepositoryTests {
    ToolTypeRepository toolTypeRepository = new ToolTypeRepository();

    @Test
    public void testRetrieveAll(){
        List<ToolType> allToolTypess = toolTypeRepository.getToolTypes();
        assertEquals(3, allToolTypess.size());
    }

    @Test
    public void testGetOne(){
        ToolType toolType = toolTypeRepository.getToolType("Chainsaw");
        assertNotNull(toolType);
        assertEquals("Chainsaw", toolType.getToolType());
        assertEquals(1.49, toolType.getPrice(), 0);
    }

    @Test
    public void testNull(){
        ToolType toolType = toolTypeRepository.getToolType("DNE");
        assertNull(toolType);
    }
    
}
