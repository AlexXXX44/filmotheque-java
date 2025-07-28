package fr.eni.tp.filmotheque.dal;

import fr.eni.tp.filmotheque.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
