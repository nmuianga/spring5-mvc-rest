package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
@Data
public class VendorDTO {

    private String name;

    @JsonProperty(value = "vendor_url")
    private String vendorUrl;
}
