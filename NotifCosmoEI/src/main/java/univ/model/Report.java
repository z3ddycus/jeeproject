package univ.model;


import univ.domain.Region;
import univ.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <b>Modèle de rapport d'effets.</b>
 *
 * Représentation d'un rapport d'effets.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * Construction d'un rapport à partir d'une description.
     * @param description La description du rapport.
     */
    public Report(String description) {
        this.description = description;
    }

    // REQUESTS

    /**
     * Retourne la description du rapport.
     *
     * @return La description du rapport.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le poids du rapport.
     *
     * @return Le poids du rapport.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Retourne les régions concernant le rapport.
     *
     * @return Les régions concernant le rapport.
     */
    public Set<Region> getRegions() {
        return regions;
    }

    /**
     * Retourne la date de la dernière déclaration.
     *
     * @return La date de déclaration.
     */
    public Date getLastDeclare() {
        return lastDeclare;
    }

    /**
     * Retourne la date de la première déclaration.
     *
     * @return La date de déclaration.
     */
    public Date getFirstDeclare() {
        return firstDeclare;
    }

    /**
     * Retourne l'utilisateur ayant déclaré pour la première fois l'effet.
     *
     * @return L'utilisateur déclarant.
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
     * Met à jour la description du rapport.
     *
     * @param description La nouvelle description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Met à jour le poids du rapport.
     *
     * @param weight Le nouveau poids.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Met à jour les régions du rapport.
     *
     * @param regions Les nouvelles régions.
     */
    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    /**
     * Met à jour la dernière date de déclaration.
     *
     * @param lastDeclare La nouvelle date.
     */
    public void setLastDeclare(Date lastDeclare) {
        this.lastDeclare = lastDeclare;
    }

    /**
     * Met à jour la première date de déclaration.
     *
     * @param firstDeclare La nouvelle date.
     */
    public void setFirstDeclare(Date firstDeclare) {
        this.firstDeclare = firstDeclare;
    }

    /**
     * Met à jour l'utilisateur déclarant du rapport.
     *
     * @param userDeclare Le nouveau déclarant.
     */
    public void setUserDeclare(User userDeclare) {
        this.userDeclare = userDeclare;
    }
}
