package com.github.sakizciadam.snake.api;

import com.github.sakizciadam.snake.api.impl.TestAPI;

import java.util.ArrayList;
import java.util.List;

public class APIManager {
    private List<API> apiList;

    public APIManager(){
        apiList=new ArrayList<API>();
        registerAPIs();
    }

    private void registerAPIs(){
        add(new TestAPI());
    }

    public void add(API api){
        apiList.add(api);
    }

    public List<API> getAPI(){
        return apiList;
    }
}
