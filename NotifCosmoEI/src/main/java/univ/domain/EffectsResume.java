package univ.domain;

import univ.domain.entity.Effect;
import univ.domain.entity.User;

import java.util.*;


public class EffectsResume {
    private Collection<Effect> effects;

    public EffectsResume(Collection<Effect> effects) {
        System.out.println("init with " + effects.size());
        this.effects = effects;
    }

    public List<InfoEffect> toList() {
        Map<String, InfoEffect> mapInfoEffect = new HashMap<>();
        for (Effect current : effects) {
            System.out.println(current.getUser().getMail() + " -- " + current.getDescription());
            InfoEffect infoEffect = mapInfoEffect.get(current.getDescription());
            if (infoEffect == null) {
                infoEffect = new InfoEffect(current.getDescription());
                mapInfoEffect.put(current.getDescription(), infoEffect);
            }
            infoEffect.actualize(current);
        }
        return new ArrayList<InfoEffect>(mapInfoEffect.values());
    }

    public class InfoEffect implements Comparable<InfoEffect> {
        private String description;
        private float weight = 0;
        private User firstUserDeclare;
        private Date firstDeclare;
        private Date lastDeclare;
        private Set<String> regions = new HashSet<>();

        public InfoEffect(String description) {
            this.description = description;
        }

        public void actualize(Effect effect) {
            if (firstDeclare == null || firstDeclare.after(effect.getDate())) {
                firstDeclare = effect.getDate();
                firstUserDeclare = effect.getUser();
            }
            if (lastDeclare == null || lastDeclare.before(effect.getDate())) {
                lastDeclare = effect.getDate();
            }
            regions.add(effect.getUser().getRegion());
            weight += effect.getUser().getWork().getWeight();
            System.out.println(firstUserDeclare.getMail() + " - " + firstDeclare + " - " + lastDeclare + " - " + description + " - " + weight);
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public User getFirstUserDeclare() {
            return firstUserDeclare;
        }

        public void setFirstUserDeclare(User firstUserDeclare) {
            this.firstUserDeclare = firstUserDeclare;
        }

        public Date getFirstDeclare() {
            return firstDeclare;
        }

        public void setFirstDeclare(Date firstDeclare) {
            this.firstDeclare = firstDeclare;
        }

        public Date getLastDeclare() {
            return lastDeclare;
        }

        public void setLastDeclare(Date lastDeclare) {
            this.lastDeclare = lastDeclare;
        }

        public Set<String> getRegions() {
            return regions;
        }

        public void setRegions(Set<String> regions) {
            this.regions = regions;
        }

        @Override
        public int compareTo(InfoEffect o) {
            if (o.weight > weight) {
                return 1;
            } else if (equals(o)) {
                return 0;
            }
            return -1;
        }
    }
}
