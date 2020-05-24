package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
