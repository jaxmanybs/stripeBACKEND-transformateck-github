package com.transformateck.Stripe.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ArticuloDto {

    @NotBlank
    private String nombre;
    @NotBlank
    private String descripcion;
    @Min(0)
    private Float precio;
    @NotBlank
    private String imagenURL;

    public ArticuloDto() {
    }

    public ArticuloDto(@NotBlank String nombre, @NotBlank String descripcion, @Min(0) Float precio, @NotBlank String imagenURL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenURL = imagenURL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

}
