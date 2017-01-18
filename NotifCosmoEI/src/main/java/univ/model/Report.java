package univ.model;


import univ.domain.Region;
import univ.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * un rapport d'effets.
 */
public class Report implements Comparable<Report> {
    /**
     * Un poids.
     */
    private double weight;

    /**
     * Une description.
     */
    private String description;

    /**
     * Les régions.
     */
    private Set<Region> regions = new HashSet<>();

    /**
     * La date de la plus vieille déclaration.
     */
    private Date lastDeclare;

    /**
     * La date de la plus récente déclaration.
     */
    private Date firstDeclare;

    /**
     * L'utilisateur ayant déclaré pour la première fois l'effet.
     */
    private User userDeclare;

    // CONSTRUCTOR

    /**
     * Un rapport.
     */
    public Report(String description) {
        this.description = description;
    }

    // REQUESTS

    /**
     * La description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Le poids.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Les régions.
     */
    public Set<Region> getRegions() {
        return regions;
    }

    /**
     * Date de la dernière déclaration.
     */
    public Date getLastDeclare() {
        return lastDeclare;
    }

    /**
     * Date de la première déclaration.
     */
    public Date getFirstDeclare() {
        return firstDeclare;
    }

    /**
     * L'utilisateur ayant déclaré pour la première fois l'effet.
     */
    public User getUserDeclare() {
        return userDeclare;
    }

    @Override
    public int compareTo(Report o) {
        if (weight < o.getWeight()) {
            return 1;
        } else if (weight > o.getWeight()) {
            return -1;
        }
        return lastDeclare.compareTo(o.getLastDeclare());
    }

    // METHODS

    /**
     * Setter de la description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter du poids.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Setter des régions.
     */
    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    /**
     * Setter de la date de dernière déclaration.
     */
    public void setLastDeclare(Date lastDeclare) {
        this.lastDeclare = lastDeclare;
    }

    /**
     * Setter de la date de première déclaration.
     */
    public void setFirstDeclare(Date firstDeclare) {
        this.firstDeclare = firstDeclare;
    }

    /**
     * Setter de l'utilisateur déclarant.
     */
    public void setUserDeclare(User userDeclare) {
        this.userDeclare = userDeclare;
    }
}
