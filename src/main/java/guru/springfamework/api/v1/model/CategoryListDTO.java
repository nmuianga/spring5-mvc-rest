package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categories;
}
