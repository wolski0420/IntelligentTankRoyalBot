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
    private final ExtraPhysicsValues values = new ExtraPhysicsValues();
    private final Rules rules = new Rules();
    private final RulesEngine rulesEngine = new DefaultRulesEngine();

    // The main method starts our bot
    public static void main(String[] args) {
        new IntelligentBot().start();
    }

    // Constructor, which loads the bot config file
    IntelligentBot() {
        super(BotInfo.fromFile("IntelligentBot.json"));
        rules.register(new RetreatRule());
        rules.register(new ByAngleShootingRule());
        rules.register(new MoveTowardsEnemyRule(values));
        rules.register(new PerpendicularMovesRule());
    }

    // Called when a new round is started -> initialize and do some movement
    @Override
    public void run() {
        // Repeat while the bot is running
        while (isRunning()) {
            turnLeft(5 * values.getTurnDirection());
        }
    }

    // We saw another bot -> fire!
    @Override
    public void onScannedBot(ScannedBotEvent e) {
        Facts facts = new Facts();
        facts.add(new Fact<>("currentBot", this));
        facts.add(new Fact<>("scannedBotEvent", e));

        rulesEngine.fire(rules, facts);
    }

    // We were hit by a bullet -> turn perpendicular to the bullet
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        Facts facts = new Facts();
        facts.add(new Fact<>("currentBot", this));
        facts.add(new Fact<>("bulletHitEvent", e));

        rulesEngine.fire(rules, facts);
    }
}
