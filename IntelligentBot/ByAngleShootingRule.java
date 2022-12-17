import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.events.ScannedBotEvent;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "typicalShootingRule", description = "Firing only when the gun is almost straight ahead the opponent direction")
public class ByAngleShootingRule {
    @Condition
    public boolean when(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        // allowing to shot only when current bot gun is almost in the same direction as scanned bot moving direction
        double angleTolerance = 10 * (scannedBotEvent.getSpeed() / currentBot.getMaxSpeed());
        double angleMax = 190 + angleTolerance;
        double angleMin = 170 - angleTolerance;

        double angleDifference = Math.abs(currentBot.getGunDirection() - scannedBotEvent.getDirection());
        return angleMin < angleDifference && angleDifference < angleMax;
    }

    @Action
    public void then(@Fact("currentBot") Bot currentBot, @Fact("scannedBotEvent") ScannedBotEvent scannedBotEvent) {
        // if further -> weaker shot, if closer -> stronger shot
        double distanceBetween = currentBot.distanceTo(scannedBotEvent.getX(), scannedBotEvent.getY());
        double diagonalLength = Math.sqrt(Math.pow(currentBot.getArenaHeight(), 2) + Math.pow(currentBot.getArenaWidth(), 2));
        double firePower = 2 * (1 - distanceBetween / diagonalLength);

        currentBot.fire(firePower);
    }
}
