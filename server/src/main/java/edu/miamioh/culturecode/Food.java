package edu.miamioh.culturecode;

import java.util.HashSet;

/**
 * Container class for representing foods on the field.
 *
 * @author Naoki Mizuno
 */
public class Food {
    public int id;
    /* What team it belongs to */
    public int team;
    /* Current coordinates */
    public Coordinates coord;

    protected CulturecodeConfigsDefault configs = null;

    public Food() {
    }

    public Food(int id, int team, Coordinates coord) {
        this.id = id;
        this.team = team;
        this.coord = coord;
    }

    /* Copy constructor */
    public Food(Food other) {
        this.id = other.id;
        this.team = other.team;
        this.coord = new Coordinates(other.coord);
        this.configs = other.configs;
    }

    public Food(int id, int team, Coordinates coord,
            CulturecodeConfigsDefault configs) {
        this(id, team, coord);
        this.configs = configs;
    }

    /**
     * Returns the set of teams that can see this food.
     */
    public HashSet<Team> seenBy() {
        HashSet<Team> ret = new HashSet<Team>();

        int[][] foodVisibility = configs.getFoodVisibility();
        for (int i = 0; i < foodVisibility.length; i++) {
            // Can see food (but may not appear as that team's food)
            if (foodVisibility[i][team] !=
                    CulturecodeConfigsDefault.INVISIBLE) {
                ret.add(configs.getWorld().getTeam(i));
            }
        }

        return ret;
    }

    /**
     * How this food looks to the given player.
     *
     * @param player the player looking at this food
     *
     * @return the team ID of the team that this food appears to belong to
     */
    public int appearsTo(Player player) {
        return appearsTo(player.teamId);
    }

    /**
     * How this food looks to the given team.
     *
     * @param team the team looking at this food
     *
     * @return the team ID of the team that this food appears to belong to
     */
    public int appearsTo(Team team) {
        return appearsTo(team.getTeamId());
    }

    /**
     * How this food looks to the given team.
     *
     * @param teamId the team looking at this food
     *
     * @return the team ID of the team that this food appears to belong to
     */
    public int appearsTo(int teamId) {
        int self = teamId;
        int other = team;

        return configs.getFoodVisibility()[self][other];
    }
}
