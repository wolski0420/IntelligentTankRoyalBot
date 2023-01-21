import dev.robocode.tankroyale.botapi.Bot;

public class ArenaBoundsValidator {
    public double calculatePossibleDistance(Bot bot, double proposedDistance) {
        double reachableXDistance = 0;
        if (bot.getDirection() < 90 || 270 < bot.getDirection()) {
            reachableXDistance = Math.max(bot.getArenaWidth() - bot.getX() - 20, 0) / Math.cos(Math.toRadians(bot.getDirection()));
        } else if (90 < bot.getDirection() && bot.getDirection() < 270){
            reachableXDistance = Math.max(bot.getX() - 20, 0) / Math.abs(Math.cos(Math.toRadians(bot.getDirection())));
        }

        double reachableYDistance = 0;
        if (0 < bot.getDirection() && bot.getDirection() < 180) {
            reachableYDistance = Math.max(bot.getArenaHeight() - bot.getY() - 20, 0) / Math.sin(Math.toRadians(bot.getDirection()));
        } else if (180 < bot.getDirection()){
            reachableYDistance = Math.max(bot.getY() - 20, 0) / Math.abs(Math.sin(Math.toRadians(bot.getDirection())));
        }

        double reachableDistance = Math.min(reachableXDistance, reachableYDistance);
        return Math.min(proposedDistance, reachableDistance);
    }
}
