package org.stage.xss.json;

public class FilterObject{

    private String hello;
    private String world;

    FilterObject(){}

    FilterObject(String hello, String world){
        this.hello = hello;
        this.world = world;
    }

    public String getHello(){
        return hello;
    }

    public String getWorld(){
        return world;
    }

    @Override
    public String toString(){
        return "FilterObject{" +
                "hello='" + hello + '\'' +
                ", world='" + world + '\'' +
                '}';
    }

}
