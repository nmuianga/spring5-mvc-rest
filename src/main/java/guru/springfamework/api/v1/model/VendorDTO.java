package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
@Data
public class VendorDTO {

    @ApiModelProperty(value = "name", required = true)
    private String name;

    @ApiModelProperty(value = "vendor_url", required = false)
    @JsonProperty(value = "vendor_url")
    private String vendorUrl;
}
