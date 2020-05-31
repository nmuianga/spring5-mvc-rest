package guru.springfamework.services;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
public class VendorServiceImplTest {

    @Mock
    VendorRepository vendorRepository;

    @InjectMocks
    VendorServiceImpl vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor");

        Mockito.when(vendorRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(vendor));

        assertNotNull(vendor);
        assertEquals(vendor.getName(), "vendor");
    }

    @Test
    public void createNewVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setVendorUrl(VendorController.BASE_URL + vendor.getId());

        Mockito.when(vendorRepository.save(ArgumentMatchers.any(Vendor.class))).thenReturn(vendor);

        VendorDTO returnDto = vendorService.createNewVendor(vendor);

        assertNotNull(returnDto);
    }
}