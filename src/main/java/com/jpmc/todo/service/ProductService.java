package com.jpmc.todo.service;

import com.jpmc.todo.dto.ProductDTO;
import com.jpmc.todo.exception.ProductAlreadyExistsException;
import com.jpmc.todo.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public ProductDTO saveProduct(ProductDTO productDTO) throws ProductAlreadyExistsException;

    public List<ProductDTO> getAllProducts();

    public ProductDTO updateProduct(int productId, ProductDTO productDTO) throws ProductNotFoundException;

    public ProductDTO getProductById(int productId) throws ProductNotFoundException;

    public void deleteProduct(int productId) throws ProductNotFoundException;


}
