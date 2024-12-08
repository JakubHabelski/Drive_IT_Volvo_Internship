package DriveIT.JH;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface country_repo extends JpaRepository<country, Integer> {
}
