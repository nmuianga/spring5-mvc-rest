package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public class CustomerServiceTest {

    public static final String FIRSTNAME = "Nilvandro";
    public static final String LASTNAME = "Muianga";
    @Mock
    CustomerRepository customerRepository;

    CustomerService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new CustomerServiceImpl(customerRepository, CustomerMapper.INSATNCE);
    }

    @Test
    public void findByFirstName() {
        //Given
        Customer c1 = new Customer();
        c1.setFirstName(FIRSTNAME);
        c1.setLastName(LASTNAME);

        //When
        Mockito.when(customerRepository.findByFirstName("Nilvandro")).thenReturn(c1);

        Assert.assertEquals(c1.getFirstName(), "Nilvandro");
        Assert.assertEquals(c1.getLastName(), "Muianga");
    }

    @Test
    public void findAllCustomers() {
        //Given
        Customer c1 = new Customer();
        c1.setFirstName("Alice");
        c1.setLastName("Macie");

        Customer c2 = new Customer();
        c2.setFirstName("Boaventura");
        c2.setLastName("Muianga");

        List<Customer> customers = Arrays.asList(c1, c2);

        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        Assert.assertEquals(2, customers.size());
    }
}