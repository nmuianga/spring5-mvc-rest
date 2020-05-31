package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Nilvandro Muianga on 5/28/2020
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @Before
    public void seutUp() throws Exception {
        System.out.println("Loading Custom Data");
        System.out.println(customerRepository.count());

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSATNCE);
    }

    @Test
    public void patchCustomerUpdateFirstName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        Assert.assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals(updatedName, updatedCustomer.getFirstName());
        Assert.assertThat(originalFirstName, Matchers.not(Matchers.equalTo(updatedCustomer.getFirstName())));
        Assert.assertThat(originalLastName, Matchers.equalTo(updatedCustomer.getLastName()));
        
    }

    @Test
    public void patchCustomerUpdateLastName() {
        String updatedName = "UpdatedName";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        Assert.assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedName);

        customerService.patchCustomer(id, customerDTO);

        Customer updatedCustomer = customerRepository.findById(id).get();

        Assert.assertNotNull(updatedCustomer);
        Assert.assertEquals(updatedName, updatedCustomer.getLastName());
        Assert.assertThat(originalLastName, Matchers.not(Matchers.equalTo(updatedCustomer.getLastName())));
        Assert.assertThat(originalFirstName, Matchers.equalTo(updatedCustomer.getFirstName()));
    }
    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Customers found: " + customers.size());

        return customers.get(0).getId();
    }
}
