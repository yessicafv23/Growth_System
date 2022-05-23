package com.Growth_System.Service;

import com.Growth_System.Entity.Producto;
import com.Growth_System.Repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    final ProductoRepository repository;

    public ProductoServiceImpl(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listaProductos() {
        return this.repository.listaProductos();
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        this.repository.save(producto);
    }

    @Override
    public void eliminar(Producto producto) {
        this.repository.delete(producto);
    }

    @Override
    public Producto encontrarProducto(Producto producto) {
        return this.repository.findById(producto.getIDProducto()).orElse(null);
    }

    @Override
    public boolean hayProducto(Producto producto){
        try {
            this.repository.findById(producto.getIDProducto()).orElse(null);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional
    public void cambiarActivo(Producto producto) {
        try {
            Producto productoEncontrado = this.repository.findById(producto.getIDProducto()).orElse(null);
            productoEncontrado.setActivoProd(0);
            this.repository.save(productoEncontrado);
        }catch (Exception ex){
            System.out.println("Producto no encontrado");
        }
    }

    @Override
    public Long totalProductos() {
        return this.repository.totalProducto();
    }

    @Override
    public Long salidasProductos() {
        return this.repository.salidasProductos();
    }

    @Override
    public List<Object> topProductos() {
        return repository.topProductos();
    }
}
