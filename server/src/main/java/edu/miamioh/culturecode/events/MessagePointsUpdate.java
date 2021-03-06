package edu.miamioh.culturecode.events;

import edu.miamioh.culturecode.Player;

/**
 * Class used for sending Points updates to clients via netty-socketio.
 *
 * @author Naoki Mizuno
 */
public class MessagePointsUpdate {
    public int teamId;
    /* The new TOTAL points for the team */
    public int newPoint;

    public MessagePointsUpdate() {
    }

    public MessagePointsUpdate(Player player, int newPoint) {
        this.teamId = player.teamId;
        this.newPoint = newPoint;
    }

    public MessagePointsUpdate(int teamId, int newPoint) {
        this.teamId = teamId;
        this.newPoint = newPoint;
    }
}
