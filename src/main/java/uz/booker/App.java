package uz.booker;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.booker.config.ProductConfig;
import uz.booker.service.ProductService;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);
        context.registerShutdownHook();
        ProductService productService = context.getBean("productService", ProductService.class);
        productService.updateProductInfo();
        context.close();
    }
}



