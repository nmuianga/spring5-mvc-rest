package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public class CustomerControllerTest {

    public static final String LASTNAME = "Muianga";
    public static final String FIRSTNAME = "Nilvandro";
    @Mock
    CustomerService service;

    @InjectMocks
    CustomerController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void findAllCustomers() throws Exception {
        CustomerDTO c1 = new CustomerDTO();
        c1.setFirstname("Nilvandro");
        c1.setLastname("Muianga");

        CustomerDTO c2 = new CustomerDTO();
        c2.setFirstname("Nilvandro");
        c2.setLastname("Muianga");

        List<CustomerDTO> customerDTOS = Arrays.asList(c1, c2);

        Mockito.when(service.findAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customers", Matchers.hasSize(2)));
    }

    @Test
    public void findByFirstName() throws Exception {
        CustomerDTO c1 = new CustomerDTO();
        c1.setLastname(LASTNAME);
        c1.setFirstname(FIRSTNAME);

        Mockito.when(service.findByFirstName("Nilvandro")).thenReturn(c1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/Nilvandro")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.equalTo(FIRSTNAME)));
    }
}