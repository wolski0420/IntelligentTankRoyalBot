import dev.robocode.tankroyale.botapi.*;
import dev.robocode.tankroyale.botapi.events.*;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

// ------------------------------------------------------------------
// IntelligentBot
// ------------------------------------------------------------------
// A sample bot original made for Robocode by Mathew Nelson.
// Ported to Robocode Tank Royale by Flemming N. Larsen.
//
// Probably the first bot you will learn about.
// Moves in a seesaw motion, and spins the gun around at each end.
// ------------------------------------------------------------------
public class IntelligentBot extends Bot {
    private final Rules rules = new Rules();
    private final RulesEngine rulesEngine = new DefaultRulesEngine();

    // The main method starts our bot
    public static void main(String[] args) {
        new IntelligentBot().start();
    }

    // Constructor, which loads the bot config file
    IntelligentBot() {
        super(BotInfo.fromFile("IntelligentBot.json"));
        rules.register(new ByAngleShootingRule());
    }

    int turnDirection = 1;

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        // Repeat while the bot is running
        while (isRunning()) {
            turnLeft(5 * turnDirection);
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        Facts facts = new Facts();
        facts.add(new Fact<>("currentBot", this));
        facts.add(new Fact<>("scannedBotEvent", e));

        rulesEngine.fire(rules, facts);
        turnToFaceTarget(e.getX(), e.getY());

        var distance = distanceTo(e.getX(), e.getY());
        forward( distance/5);

        rescan();
    }

    private void turnToFaceTarget(double x, double y) {
        var bearing = bearingTo(x, y);
        if (bearing >= 0) {
            turnDirection = 1;
        } else {
            turnDirection = -1;
        }
        turnLeft(bearing);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        // Calculate the bearing to the direction of the bullet
        var bearing = calcBearing(e.getBullet().getDirection());

        // Turn 90 degrees to the bullet direction based on the bearing
        turnLeft(90 - bearing);
    }
}
