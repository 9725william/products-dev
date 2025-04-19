package com.products.demo.api.v1.local.api_franchise.product.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.product.adapters.bd2.Product;
import com.products.demo.api.v1.local.api_franchise.product.adapters.bd2.ProductRepository;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class ProductAdapter {

    private String myClassName = ProductAdapter.class.getName();
    
    @Autowired
    ProductRepository productRepository;

    public Object createOrUpdate(Product product) {
        try {
            Object resp = productRepository.save(product);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando el producto",
                    e.getMessage(),
                    myClassName);
        }
    }

        public Object getById(Long id) {
        try {
            Optional<Product> resp = productRepository.findById(id);
            if (!resp.isPresent()) {
                return null;
            }
            return resp.get();
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error buscando el registro",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object deleteProductById( Long id )
	{
		try {
			productRepository.deleteById( id );
			return true;
		}
		catch ( Exception e ){
			return new ErrorService(
				"Ha ocurrido un error eliminando el producto",
				e.getMessage(),
				myClassName
			);
		}
	}
}
