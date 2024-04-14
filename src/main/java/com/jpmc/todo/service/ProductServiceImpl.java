package com.jpmc.todo.service;

import com.jpmc.todo.dto.ProductDTO;
import com.jpmc.todo.exception.ProductAlreadyExistsException;
import com.jpmc.todo.exception.ProductNotFoundException;
import com.jpmc.todo.model.ProductEntity;
import com.jpmc.todo.repository.ProductRepository;
import com.jpmc.todo.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) throws ProductAlreadyExistsException {
        ProductEntity productEntity = productRepository.save((ProductEntity) EntityDTOConverter.convertToEntity(productDTO));
        return (ProductDTO) EntityDTOConverter.convertToDTO(productEntity);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductDTO updateProduct(int productId, ProductDTO productDTO) throws ProductNotFoundException {
        return null;
    }

    @Override
    public ProductDTO getProductById(int productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteProduct(int productId) throws ProductNotFoundException {

    }
}
