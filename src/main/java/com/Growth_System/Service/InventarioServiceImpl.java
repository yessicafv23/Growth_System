package com.Growth_System.Service;

import com.Growth_System.Entity.Inventario;
import com.Growth_System.Repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventarioServiceImpl implements InventarioService {

    final InventarioRepository repository;

    public InventarioServiceImpl(InventarioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> getAllInventarios() {
        return this.repository.listaInventarios();
    }

    @Override
    @Transactional
    public void guardar(Inventario inventario) {
        this.repository.save(inventario);
    }

    @Override
    public void eliminar(Inventario inventario) {
        this.repository.delete(inventario);
    }


    @Override
    public Inventario encontrarInventario(Inventario inventario) {
        return this.repository.findById(inventario.getIDInventario()).orElse(null);
    }

    @Override
    public boolean hayProducto(Inventario inventario) {
        try{
            this.repository.findById(inventario.getIDInventario()).orElse(null);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public void cambiarActivo(Inventario inventario) {
        try{
            Inventario inventarioEncontrado = this.repository.findById(inventario.getIDInventario()).orElse(null);
            inventarioEncontrado.setActivoInv(0);
            this.repository.save(inventarioEncontrado);
        }catch (Exception ex){
            System.out.println("Producto no encontrado");
        }
    }
}
