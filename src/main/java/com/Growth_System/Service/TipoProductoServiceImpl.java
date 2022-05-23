package com.Growth_System.Service;

import com.Growth_System.Entity.TipoProducto;
import com.Growth_System.Repository.TipoProductoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    final TipoProductoRepository repository;

    public TipoProductoServiceImpl(TipoProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TipoProducto> getAllTipoP() {
        return this.repository.listaTipoProductos();
    }

    @Override
    @Transactional
    public void guardar(TipoProducto tipo) {
        this.repository.save(tipo);
    }

    @Override
    public void eliminar(TipoProducto tipo) {
        this.repository.delete(tipo);
    }

    @Override
    public TipoProducto encontrarTipo(TipoProducto tipo) {
        return repository.findById(tipo.getIDTipo()).orElse(null);
    }

    @Override
    public boolean hayProducto(TipoProducto tipoProducto) {
        try {
            this.repository.findById(tipoProducto.getIDTipo()).orElse(null);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    @Transactional
    public void cambiarActivo(TipoProducto tipoProducto) {
        try{
            TipoProducto tipoEncontrado = this.repository.findById(tipoProducto.getIDTipo()).orElse(null);
            tipoEncontrado.setActivoTipo(0);
            this.repository.save(tipoEncontrado);
        }catch (Exception ex){
            System.out.println("Producto no encontrado");
        }
    }
}
