package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public class CategoryServiceImplTest {

    public static final String UNDERGROUND = "Underground";
    public static final long ID = 1L;
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryServiceImpl categoryService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() {
        //Given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        //when
        Mockito.when(categoryRepository.findAll()).thenReturn(categories);

        //then
        assertEquals(3, categoryService.getAllCategories().size());
    }

    @Test
    public void getCategoryByName() {
        //Given
        Category c1 = new Category();
        c1.setId(ID);
        c1.setName(UNDERGROUND);

        //when
        Mockito.when(categoryRepository.findByName(UNDERGROUND)).thenReturn(c1);

        Category found = c1;

        assertEquals(c1.getId().longValue(), ID);
        assertEquals(c1.getName(), UNDERGROUND);
    }
}