package guru.springfamework.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Nilvandro Muianga on 5/24/2020
 */
@Data
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstname;
    private String lastname;
    private String customerUrl;
}
