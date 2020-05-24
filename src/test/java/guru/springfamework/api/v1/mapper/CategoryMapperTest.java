package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public class CategoryMapperTest {

    public static final String BOOKS = "Books";
    public static final Long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDto() {
        Category category = new Category();
        category.setName(BOOKS);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDto(category);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(BOOKS, categoryDTO.getName());
    }
}