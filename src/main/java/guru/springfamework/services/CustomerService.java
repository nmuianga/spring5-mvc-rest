package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public interface CustomerService {

    CustomerDTO findByFirstName(String firstName);

    List<CustomerDTO> findAllCustomers();

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

    void deleteById(Long id);
}
