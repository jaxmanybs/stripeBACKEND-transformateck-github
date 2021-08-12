package com.transformateck.Stripe.service;

import com.transformateck.Stripe.model.Articulo;
import com.transformateck.Stripe.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public List<Articulo> lista(){
        List<Articulo> lista = articuloRepository.findAll();
        return lista;
    }

    public Optional<Articulo> getOne(int id){
        return articuloRepository.findById(Long.valueOf(id));
    }

    public Optional<Articulo> getById(long id){
        return articuloRepository.findById(id);
    }

    public Optional<Articulo> getByNombre(String nombre){
        return articuloRepository.findByNombre(nombre);
    }

    public void save(Articulo articulo){
        articuloRepository.save(articulo);
    }

    public void delete(long id){
        articuloRepository.deleteById(id);
    }

    public boolean existsId(long id){
        return articuloRepository.existsById(id);
    }

    public boolean existsNombre(String nombre){
        return articuloRepository.existsByNombre(nombre);
    }
}