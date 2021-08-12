package com.transformateck.Stripe.controller;

import com.transformateck.Stripe.dto.ArticuloDto;
import com.transformateck.Stripe.http.Mensaje;
import com.transformateck.Stripe.model.Articulo;
import com.transformateck.Stripe.service.ArticuloService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulo")
@CrossOrigin(origins = "*")
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/lista")
    public ResponseEntity<List<Articulo>> lista(){
        List<Articulo> lista = articuloService.lista();
        return new ResponseEntity<List<Articulo>>(lista, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Articulo> detalle(@PathVariable("id") long id){
        if(!articuloService.existsId(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Articulo articulo = articuloService.getById(id).get();
        return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> crear(@RequestBody Articulo articulo){
        if(StringUtils.isBlank(articulo.getNombre()))
            return new ResponseEntity(new Mensaje("nombre obligatorio"), HttpStatus.BAD_REQUEST);
        if(articulo.getPrecio() == null || articulo.getPrecio() < 1)
            return new ResponseEntity(new Mensaje("precio obligatorio"), HttpStatus.BAD_REQUEST);
        if(articuloService.existsNombre(articulo.getNombre()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(articulo.getPrecio()==null || articulo.getPrecio()<1000 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 10"), HttpStatus.BAD_REQUEST);
        articuloService.save(articulo);
        return new ResponseEntity(new Mensaje("artículo creado"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody ArticuloDto articuloDto){
        if(!articuloService.existsId(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(articuloService.existsNombre(articuloDto.getNombre()) && articuloService.getByNombre(articuloDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(articuloDto.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(articuloDto.getPrecio()==null || articuloDto.getPrecio()<1000 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 10"), HttpStatus.BAD_REQUEST);

        Articulo articulo = articuloService.getOne(id).get();
        articulo.setNombre(articuloDto.getNombre());
        articulo.setDescripcion(articuloDto.getDescripcion());
        articulo.setPrecio(articuloDto.getPrecio());
        articulo.setImagenURL(articuloDto.getImagenURL());
        articuloService.save(articulo);
        return new ResponseEntity(new Mensaje("Articulo actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!articuloService.existsId(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        articuloService.delete(id);
        return new ResponseEntity(new Mensaje("articulo eliminado"), HttpStatus.OK);
    }
}
