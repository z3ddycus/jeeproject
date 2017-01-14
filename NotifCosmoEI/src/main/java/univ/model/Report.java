package univ.model;


import univ.domain.Region;
import univ.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Report implements Comparable<Report> {
    private double weight;
    private String description;
    private Set<Region> regions;
    private Date lastDeclare;
    private Date firstDeclare;
    private User userDeclare;

    public Report(String description) {
        this.regions = new HashSet<>();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public Date getLastDeclare() {
        return lastDeclare;
    }

    public void setLastDeclare(Date lastDeclare) {
        this.lastDeclare = lastDeclare;
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

    public Date getFirstDeclare() {
        return firstDeclare;
    }

    public void setFirstDeclare(Date firstDeclare) {
        this.firstDeclare = firstDeclare;
    }

    public User getUserDeclare() {
        return userDeclare;
    }

    public void setUserDeclare(User userDeclare) {
        this.userDeclare = userDeclare;
    }
}
