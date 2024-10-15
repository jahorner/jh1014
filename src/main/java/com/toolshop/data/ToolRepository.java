package com.toolshop.data;

import java.util.ArrayList;
import java.util.List;

import com.toolshop.model.Tool;
public class ToolRepository {
    
    private final List<Tool> tools = new ArrayList<>();

    public ToolRepository(ToolTypeRepository toolTypeRepository){
        tools.add(new Tool("CHNS",toolTypeRepository.getToolType("Chainsaw"),"Stihl"));
        tools.add(new Tool("LADW",toolTypeRepository.getToolType("Ladder"),"Werner"));
        tools.add(new Tool("JAKD",toolTypeRepository.getToolType("Jackhammer"),"DeWalt"));
        tools.add(new Tool("JAKR",toolTypeRepository.getToolType("Jackhammer"),"Ridgid"));
        tools.add(new Tool("CHNW",null,"Werner"));
    }

    public List<Tool> getTools() {
        return tools;
    }

    public Tool getTool(String toolCode){
        return tools.stream().filter(t -> t.getCode().equals(toolCode)).findFirst().orElse(null);
    }
}