package ioproj.example.spring.ioproj.db;

import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<ScoreRow, Long> {
}
