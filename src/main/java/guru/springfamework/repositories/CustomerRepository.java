package guru.springfamework.repositories;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Nilvandro Muinga on 5/24/2020
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByFirstName(String firstName);

}
