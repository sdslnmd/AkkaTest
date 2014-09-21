package helloworkd;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class JavaBotMain {
    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("helloAkka");
        ActorRef akkaBot= actorSystem.actorOf(Props.create(JavaAkkaBot.class), JavaAkkaBot.class.getSimpleName());

        akkaBot.tell(new Move(Direction.FORWARD), ActorRef.noSender());
        akkaBot.tell(new Move(Direction.BACKWARDS), ActorRef.noSender());
        akkaBot.tell(new Stop(), ActorRef.noSender());

        System.out.println("java bot main Actor system create");

    }


    public static class Move{
        public final Direction direction;
        public Move(Direction direction) {
            this.direction = direction;
        }
    }
    public static class Stop{}
    public static class RobotState{
        public final Direction direction;
        public final boolean moving;

        public RobotState(Direction direction, boolean moving) {
            this.direction = direction;
            this.moving = moving;
        }
    }
}
