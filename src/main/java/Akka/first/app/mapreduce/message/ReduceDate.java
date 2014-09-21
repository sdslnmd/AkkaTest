package Akka.first.app.mapreduce.message;

import java.util.HashMap;
import java.util.Map;

public class ReduceDate {
    private final Map<String, Integer> reduceDateList;

    public Map<String, Integer> getReduceDateList() {
        return reduceDateList;
    }

    public ReduceDate(Map<String, Integer> reduceDateList) {
        this.reduceDateList = reduceDateList;
    }

}
