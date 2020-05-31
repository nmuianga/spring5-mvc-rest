package guru.springfamework.repositories;

import guru.springfamework.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nilvandro Muianga on 5/29/2020
 */
public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
