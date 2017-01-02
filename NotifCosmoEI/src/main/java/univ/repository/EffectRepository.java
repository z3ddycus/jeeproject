package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Effect;

public interface EffectRepository  extends JpaRepository<Effect, Long> {
}
