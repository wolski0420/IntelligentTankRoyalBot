import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "moveTowardsEnemyRule", description = "Moving towards detected enemy bot")
public class MoveTowardsEnemyRule {
    private final ExtraPhysicsValues values;

    public MoveTowardsEnemyRule(ExtraPhysicsValues values) {
        this.values = values;
    }

    @Condition
    public boolean when(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        // can be done almost always
        return true;
    }

    @Action
    public void then(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        // directing and going to enemy bot, trying to fire it
        turnToFaceTarget(currentBot, scannedBotEvent.getX(), scannedBotEvent.getY());

        currentBot.fire(1);
        currentBot.forward( currentBot.distanceTo(scannedBotEvent.getX(), scannedBotEvent.getY()) / 2);

        currentBot.rescan();
    }

    private void turnToFaceTarget(Bot currentBot, double x, double y) {
        var bearing = currentBot.bearingTo(x, y);
        values.setTurnDirection(bearing >= 0 ? 1 : -1);

        currentBot.turnLeft(bearing);
    }
}
