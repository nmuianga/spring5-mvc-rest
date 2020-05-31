package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;

import java.util.List;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
public interface VendorService {

    VendorDTO getById(Long id);

    List<VendorDTO> getAll();

    VendorDTO createNewVendor(Vendor vendor);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void delete(Long id);
}
