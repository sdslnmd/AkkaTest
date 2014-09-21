package Akka.first.app.mapreduce.actors;

import Akka.first.app.mapreduce.message.MapData;
import Akka.first.app.mapreduce.message.WordCount;
import akka.actor.UntypedActor;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.StringTokenizer;

public class MapActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String work = (String) message;
            getSender().tell(evaluateExpression(work), this.getSelf());
        } else {
            unhandled(message);
        }
    }

    private MapData evaluateExpression(String line) {
        List<WordCount> dataList = Lists.newArrayList();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase();
            dataList.add(new WordCount(word, 1));
        }
        return new MapData(dataList);
    }
}
