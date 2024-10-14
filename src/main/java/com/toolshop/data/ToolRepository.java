package com.toolshop.data;

import java.util.ArrayList;
import java.util.List;

import com.toolshop.model.Tool;

public class ToolRepository {
    
    private final List<Tool> tools = new ArrayList<>();

    public ToolRepository(){
        tools.add(new Tool("CHNS","Chainsaw","Stihl"));
        tools.add(new Tool("LADW","Ladder","Werner"));
        tools.add(new Tool("JAKD","Jackhammer","DeWalt"));
        tools.add(new Tool("JAKR","Jackhammer","Ridgid"));
    }

    public List<Tool> getTools() {
        return tools;
    }
}