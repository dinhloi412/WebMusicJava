package tdmu.music.web.pm03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdmu.music.web.pm03.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
