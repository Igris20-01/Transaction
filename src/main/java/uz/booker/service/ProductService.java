package uz.booker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.booker.dto.Product;
import uz.booker.repository.ProductRepo;


@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Transactional // connection start
    public void saveProductInfo() {
        for(long i = 1; i <= 10; i++){
            Product product = new Product();
            product.setId(i);
            product.setName("Test Product " + i);
            productRepo.saveProduct(product);
        }
        //commit
        //close
    }

    public void updateProductInfo() {
        Product product = productRepo.findById(1L);
        product.setName("New Name");
        productRepo.updateProduct(product);
        productRepo.findAll(product);

    }


}
