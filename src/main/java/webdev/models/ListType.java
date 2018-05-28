package webdev.models;

import java.util.HashMap;
import java.util.Map;

public enum ListType {
    UNORDERED(0), ORDERED(1);

    private int dbValue;

    private ListType(int dbValue){
        this.dbValue=dbValue;
    }

    public int getDbValue(){
        return dbValue;
    }

    private static final Map<Integer,ListType> lookup =  new HashMap<Integer,ListType>();
    static {
        for(ListType r : ListType.values())
            lookup.put(r.getDbValue(), r);
    }

    public static ListType get(int num) {
        return lookup.get(num);
    }
}
