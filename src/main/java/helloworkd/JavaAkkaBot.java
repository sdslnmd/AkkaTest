package helloworkd;

import akka.actor.UntypedActor;

public class JavaAkkaBot extends UntypedActor {

    Direction direction = Direction.BACKWARDS;

    boolean moving = false;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof JavaBotMain.Move) {
            direction = ((JavaBotMain.Move) message).direction;
            moving = true;
            System.out.println(this.getClass().getSimpleName() + direction);
        }
        else if (message instanceof JavaBotMain.Stop) {
            moving = false;
            System.out.println(this.getClass().getSimpleName() + "stop");
        } else {
            System.out.println(this.getClass().getSimpleName() + "unhandled");
            unhandled(message);
        }
    }

}
