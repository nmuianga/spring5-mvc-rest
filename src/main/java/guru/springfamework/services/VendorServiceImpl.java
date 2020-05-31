package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public VendorDTO getById(Long id) {
        Vendor vendor = this.vendorRepository.findById(id).get();

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + vendor.getId());
        return vendorDTO;
    }

    @Override
    public List<VendorDTO> getAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(vendor);
                    vendorDTO.setVendorUrl(VendorController.BASE_URL + vendor.getId());
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO createNewVendor(Vendor vendor) {
       return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if (vendorDTO.getName() != null) {
                        vendor.setName(vendorDTO.getName());
                    }

                    VendorDTO returnDto = vendorMapper.vendorToVendorDto(vendor);
                    returnDto.setVendorUrl(VendorController.BASE_URL + vendor.getId());

                    return returnDto;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        this.vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDto(savedVendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + vendor.getId());

        return vendorDTO;
    }
}
