package guru.springfamework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Nilvandro Muianga on 5/30/2020
 */
@Data
@AllArgsConstructor
public class VendorListDTO {

    private List<VendorDTO> vendors;
}
