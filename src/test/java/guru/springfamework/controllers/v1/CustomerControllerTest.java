package guru.springfamework.controllers.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void createNewCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);
        customerDTO.setLastname(LASTNAME);
        customerDTO.setCustomerUrl("/api/customers/1");

        Mockito.when(service.createNewCustomer(ArgumentMatchers.any())).thenReturn(customerDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.equalTo("Nilvandro")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerUrl", Matchers.equalTo("/api/customers/1")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Ricardo");
        customer.setLastname("Muchanga");

        CustomerDTO returnedDto = new CustomerDTO();
        returnedDto.setLastname(customer.getLastname());
        returnedDto.setFirstname(customer.getFirstname());
        returnedDto.setCustomerUrl("/api/v1/customers/1");

        Mockito.when(service.saveCustomerByDTO(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CustomerDTO.class)))
                .thenReturn(returnedDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", CoreMatchers.equalTo("Ricardo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", CoreMatchers.equalTo("Muchanga")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerUrl", CoreMatchers.equalTo("/api/v1/customers/1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Nino");

        CustomerDTO returnDto = new CustomerDTO();
        returnDto.setFirstname(customerDTO.getFirstname());
        returnDto.setLastname("Mangane");
        returnDto.setCustomerUrl("/api/v1/customers/1");

        Mockito.when(service.patchCustomer(ArgumentMatchers.anyLong(), ArgumentMatchers.any(CustomerDTO.class))).thenReturn(returnDto);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(AbstractRestControllerTest.asJsonString(customerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname", Matchers.equalTo("Nino")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname", Matchers.equalTo("Mangane")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerUrl", Matchers.equalTo("/api/v1/customers/1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(service, Mockito.times(1)).deleteById(ArgumentMatchers.anyLong());
    }
}