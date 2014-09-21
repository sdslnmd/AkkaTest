package Akka;

import akka.actor.ActorRef;
import akka.actor.OneForOneStrategy;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

import java.io.Serializable;

public class SampleActor extends UntypedActor {

    public static class Replay implements Serializable {
        final private ActorRef actorRef;
        public Replay(ActorRef actorRef) {
            this.actorRef = actorRef;
        }
    }



    @Override
    public void onReceive(Object o) throws Exception {

    }
}
