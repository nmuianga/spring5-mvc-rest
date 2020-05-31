package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "name", target = "name")
    VendorDTO vendorToVendorDto(Vendor vendor);

    @Mapping(source = "name", target = "name")
    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
