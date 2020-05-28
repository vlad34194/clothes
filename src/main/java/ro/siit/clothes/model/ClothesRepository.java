package ro.siit.clothes.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes,Long> {
    List<Clothes> findAllByCategoryOrderByDateUpdate(Category category);
    List<Clothes> findAll();
    List<Clothes> findByTitle(String title);
}
