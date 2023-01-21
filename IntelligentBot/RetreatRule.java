import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "retreatRule", description = "When you are close, take a fire and retreat.")
public class RetreatRule {
    private final ArenaBoundsValidator arenaBoundsValidator = new ArenaBoundsValidator();

    @Condition
    public boolean when(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        double distanceBetween = currentBot.distanceTo(scannedBotEvent.getX(), scannedBotEvent.getY());
        var bearingFromGun = currentBot.gunBearingTo(scannedBotEvent.getX(), scannedBotEvent.getY());
        return distanceBetween < 50 && Math.abs(bearingFromGun) <= 3;
    }

    @Action
    public void then(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        currentBot.fire(4);
        currentBot.setMaxSpeed(5);
        currentBot.forward(arenaBoundsValidator.calculatePossibleDistance(currentBot, 10_000));
        currentBot.setTurnLeft(0);
    }
}
