package uz.booker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.booker.config.ProductMapper;
import uz.booker.dto.Product;

import java.util.List;

@Repository
public class ProductRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProduct(Product product){
        String sql = "INSERT INTO PRODUCT VALUES (?,?)";
        Object[] args = {product.getId(), product.getName()};
        jdbcTemplate.update(sql, args);
        System.out.println("Product saved..");
    }
    public Product findById(Long id) {
        String sql = "SELECT * FROM PRODUCT WHERE id = ?";
        Object[] args = {id};
        try {
            return jdbcTemplate.queryForObject(sql, args, new ProductMapper());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public void updateProduct(Product product){
        String sql = "UPDATE PRODUCT SET name = ? WHERE id = ?";
        Object[] args = {product.getName(),product.getId()};
        jdbcTemplate.update(sql, args);
        System.out.println("Product updated..");
    }

    public List<Product> findAll(Product product) {
        String sql = "SELECT * FROM product";
        List<Product> customers = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(Product.class));
        return customers;
    }
}


