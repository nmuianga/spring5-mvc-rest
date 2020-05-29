package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.services.CategoryService;
import guru.springfamework.services.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public class CategoryControllerTest {

    public static final String JAVA = "Java";
    public static final long ID = 1L;
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void getAllCategories() throws Exception {
        //Given
        CategoryDTO c1 = new CategoryDTO();
        c1.setId(1L);
        c1.setName("Romance");

        CategoryDTO c2 = new CategoryDTO();
        c2.setId(2L);
        c2.setName("Programacao");

        List<CategoryDTO> categories = Arrays.asList(c1, c2);

        //when
        Mockito.when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

    }

    @Test
    public void getCategoryByName() throws Exception {
        CategoryDTO c1 = new CategoryDTO();
        c1.setName(JAVA);
        c1.setId(ID);

        Mockito.when(categoryService.getCategoryByName(JAVA)).thenReturn(c1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/Java")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(JAVA)));
    }

    @Test
    public void testFindByNameNotFound() throws Exception {
        Mockito.when(categoryService.getCategoryByName(ArgumentMatchers.anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}