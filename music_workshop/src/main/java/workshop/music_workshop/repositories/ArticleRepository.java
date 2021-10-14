package workshop.music_workshop.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workshop.music_workshop.model.entities.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Query("select a from ArticleEntity as a order by a.createdOn")
    List<ArticleEntity> findAllOrderByCreatedOn();

}
