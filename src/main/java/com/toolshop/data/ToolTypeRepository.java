package com.toolshop.data;

import java.util.ArrayList;
import java.util.List;

import com.toolshop.model.ToolType;

public class ToolTypeRepository {
    
    private final List<ToolType> toolTypes = new ArrayList<>();

    // Ladder $1.99 Yes Yes No
    // Chainsaw $1.49 Yes No Yes
    // Jackhammer $2.99 Yes No No
    public ToolTypeRepository(){
        toolTypes.add(new ToolType("Ladder",1.99,true, true, false));
        toolTypes.add(new ToolType("Chainsaw",1.49, true, false, true));
        toolTypes.add(new ToolType("Jackhammer",2.99, true, false, false));
    }

    public List<ToolType> getToolTypes() {
        return toolTypes;
    }

    public ToolType getToolType(String toolType){
        return toolTypes.stream().filter(t -> t.getToolType().equals(toolType)).findFirst().orElse(null);
    }
}