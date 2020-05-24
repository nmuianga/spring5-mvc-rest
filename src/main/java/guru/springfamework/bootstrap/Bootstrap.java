package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Nilvandro Muinga on 5/23/2020
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category drieds = new Category();
        drieds.setName("Drieds");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(drieds);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        loadCustomers();

        System.out.println("Categories loaded: " + categoryRepository.count());
        System.out.println("Customers loaded: " + customerRepository.count());
    }

    public void loadCustomers() {
        Customer nino = new Customer();
        nino.setFirstName("Nilvandro");
        nino.setLastName("Muianga");

        Customer clayton = new Customer();
        clayton.setFirstName("Clayton");
        clayton.setLastName("Muianga");

        Customer alice = new Customer();
        alice.setFirstName("Alice");
        alice.setLastName("Macie");

        customerRepository.save(nino);
        customerRepository.save(alice);
        customerRepository.save(clayton);
    }
}
