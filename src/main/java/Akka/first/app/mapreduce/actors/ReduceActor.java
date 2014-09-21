package Akka.first.app.mapreduce.actors;

import Akka.first.app.mapreduce.message.MapData;
import Akka.first.app.mapreduce.message.ReduceDate;
import Akka.first.app.mapreduce.message.WordCount;
import akka.actor.UntypedActor;
import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ReduceActor extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {

        if (o instanceof MapData) {
            MapData mapData = (MapData) o;
            getSender().tell(reduce(mapData.getDataList()), getSelf());
        } else {
            unhandled(o);
        }
    }

    private ReduceDate reduce(List<WordCount> dataList) {
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        for (WordCount wordCount : dataList) {
            multimap.put(wordCount.getWord(), 1);
        }

        Map<String, Collection<Integer>> stringCollectionMap = multimap.asMap();
        Map<String, Integer> stringObjectMap = Maps.transformValues(stringCollectionMap, new Function<Collection<Integer>, Integer>() {
            @Override
            public Integer apply(Collection<Integer> integers) {
                return integers.size();
            }
        });

        return new ReduceDate(stringObjectMap);

    }
}
