package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.services.VendorService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
public class VendorControllerTest {

    @Mock
    VendorService vendorService;

    @InjectMocks
    VendorController vendorController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void getById() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("vendor");

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "1");

        Mockito.when(vendorService.getById(ArgumentMatchers.anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(MockMvcRequestBuilders.get(VendorController.BASE_URL + "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("vendor")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vendorUrl", Matchers.equalTo("/api/v1/vendors/1")));

    }
}