package Akka.first.app.mapreduce.actors;

import Akka.first.app.mapreduce.message.Result;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;


public class MapReduceApplication {
    public static void main(String[] args) throws Exception {
        ActorSystem _system = ActorSystem.create("mapReduceAPP");
        ActorRef master = _system.actorOf(new Props(MasterActor.class), "master");
        master.tell("The quick brown fox tried to jump over the lazy dog and fell on the dog", master);
        master.tell("Dog is man's best friend", master);
        master.tell("Dog and Fox belong to the same family", master);
        Thread.sleep(2000);

        Timeout timeout = new Timeout(new FiniteDuration(5, TimeUnit.SECONDS));

        Future<Object> ask = Patterns.ask(master, new Result(), timeout);
        Object result = Await.result(ask, timeout.duration());
        System.out.println(result);

        _system.shutdown();

    }
}
