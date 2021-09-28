package tdmu.music.web.pm03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdmu.music.web.pm03.model.Category;
import tdmu.music.web.pm03.repository.CategoryRepository;
import tdmu.music.web.pm03.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Category> getAllCategory()
    {
        return categoryRepository.findAll();
    }
    public void addCategory(Category category)
    {
        categoryRepository.save(category);

    }
    public void removeCategoryById(int id)
    {
        categoryRepository.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id)
    {
        return categoryRepository.findById(id);
    }

}
