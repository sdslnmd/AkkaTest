package Akka.first.app.mapreduce.actors;

import Akka.first.app.mapreduce.message.MapData;
import Akka.first.app.mapreduce.message.ReduceDate;
import Akka.first.app.mapreduce.message.Result;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinRouter;

public class MasterActor extends UntypedActor {
    ActorRef mapActor = getContext().actorOf(new Props(MapActor.class).withRouter(new RoundRobinRouter(5)), "map");
    ActorRef reduceActor = getContext().actorOf(new Props(ReduceActor.class).withRouter(new RoundRobinRouter(5)), "reduce");
    ActorRef aggregateActor = getContext().actorOf(new Props(AggregateActor.class).withRouter(new RoundRobinRouter(5)), "aggregate");

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof String) {
            String s = (String) o;
            mapActor.tell(s, getSelf());

        } else if (o instanceof MapData) {
            MapData mapData = (MapData) o;
            reduceActor.tell(mapData, getSelf());

        } else if (o instanceof ReduceDate) {
            ReduceDate reduceDate = (ReduceDate) o;
            aggregateActor.tell(reduceDate, getSelf());

        } else if (o instanceof Result) {
            Result result = (Result) o;
            aggregateActor.forward(result, getContext());
        } else {
            unhandled(o);
        }


    }
}
