package Akka.first.app.mapreduce.actors;

import Akka.first.app.mapreduce.message.ReduceDate;
import Akka.first.app.mapreduce.message.Result;
import akka.actor.UntypedActor;
import com.google.common.collect.Maps;

import java.util.Map;

public class AggregateActor extends UntypedActor {

    private static Map<String, Integer> finalReduceMap = Maps.newHashMap();

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof ReduceDate) {
            ReduceDate reduceDate = (ReduceDate) o;
            aggregateInMemoryReduce(reduceDate.getReduceDateList());
        }else if (o instanceof Result) {
            getSender().tell(finalReduceMap.toString(), getSelf());
        } else {
            unhandled(o);
        }

    }

    private void aggregateInMemoryReduce(Map<String, Integer> reduceMap) {
        for (String s : reduceMap.keySet()) {
            if (finalReduceMap.get(s) != null) {
                int i = reduceMap.get(s) + finalReduceMap.get(s);
                finalReduceMap.put(s, i);
            } else {
                finalReduceMap.put(s, reduceMap.get(s));
            }
        }
    }
}
