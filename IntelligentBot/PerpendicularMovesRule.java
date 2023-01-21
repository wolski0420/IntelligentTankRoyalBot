import dev.robocode.tankroyale.botapi.Bot;
import dev.robocode.tankroyale.botapi.events.HitByBulletEvent;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "perpendicularMovesRule", description = "After being shot, moving perpendicularly respectively to bullet direction.")
public class PerpendicularMovesRule {
    private final ArenaBoundsValidator arenaBoundsValidator = new ArenaBoundsValidator();

    @Condition
    public boolean when(@Fact("currentBot") Bot currentBot, @Fact("bulletHitEvent") HitByBulletEvent event) {
        return true;
    }

    @Action
    public void then(@Fact("currentBot") Bot currentBot, @Fact("bulletHitEvent") HitByBulletEvent event) {
        // Calculate the bearing to the direction of the bullet
        var bearing = currentBot.calcBearing(event.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        currentBot.turnLeft(90 - bearing);
        currentBot.forward(arenaBoundsValidator.calculatePossibleDistance(currentBot, 100));
    }
}
