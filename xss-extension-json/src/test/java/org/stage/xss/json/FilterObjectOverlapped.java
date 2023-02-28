package org.stage.xss.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterObjectOverlapped{

    private FilterObject filterObject;

    private Map<String, FilterObject> filterObjectMap;

    private List<FilterObject> filterObjectList;

    FilterObjectOverlapped(){}

    FilterObjectOverlapped(String invalid){
        filterObject = new FilterObject(invalid, invalid);
        filterObjectMap = new HashMap<>();
        filterObjectMap.put(invalid, new FilterObject(invalid, invalid));
        filterObjectList = new ArrayList<>();
        filterObjectList.add(new FilterObject(invalid, invalid));
    }

    public FilterObject getFilterObject(){
        return filterObject;
    }

    public Map<String, FilterObject> getFilterObjectMap(){
        return filterObjectMap;
    }

    public List<FilterObject> getFilterObjectList(){
        return filterObjectList;
    }

    @Override
    public String toString(){
        return "FilterObjectOverlapped{" +
                "filterObject=" + filterObject.toString() +
                ", filterObjectMap=" + filterObjectMap +
                ", filterObjectList=" + filterObjectList +
                '}';
    }

}
