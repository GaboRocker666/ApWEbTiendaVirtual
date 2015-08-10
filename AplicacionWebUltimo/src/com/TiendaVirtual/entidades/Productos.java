package com.TiendaVirtual.entidades;

import java.awt.Image;



public class Productos {
private int id;
private Categorias categoria;
private String nombre_producto;
private String descripcion;
private int stock_minimo;
private int stock_actual;
private double precio;
private String imagen;
private Image imagen2;
private org.zkoss.zul.Image imagen3;

public org.zkoss.zul.Image getImagen3() {
	return imagen3;
}

public void setImagen3(org.zkoss.zul.Image imagen3) {
	this.imagen3 = imagen3;
}

private String estado;

public Productos() {
	super();
	// TODO Auto-generated constructor stub
}

public Productos(int id, Categorias categoria, String nombre_producto,
		String descripcion, int stock_minimo, int stock_actual, double precio,
		String imagen, Image imagen2, String estado) {
	super();
	this.id = id;
	this.categoria = categoria;
	this.nombre_producto = nombre_producto;
	this.descripcion = descripcion;
	this.stock_minimo = stock_minimo;
	this.stock_actual = stock_actual;
	this.precio = precio;
	this.imagen = imagen;
	this.imagen2 = imagen2;
	this.estado = estado;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Categorias getCategoria() {
	return categoria;
}

public void setCategoria(Categorias categoria) {
	this.categoria = categoria;
}

public String getNombre_producto() {
	return nombre_producto;
}

public void setNombre_producto(String nombre_producto) {
	this.nombre_producto = nombre_producto;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public int getStock_minimo() {
	return stock_minimo;
}

public void setStock_minimo(int stock_minimo) {
	this.stock_minimo = stock_minimo;
}

public int getStock_actual() {
	return stock_actual;
}

public void setStock_actual(int stock_actual) {
	this.stock_actual = stock_actual;
}

public double getPrecio() {
	return precio;
}

public void setPrecio(double precio) {
	this.precio = precio;
}

public String getImagen() {
	return imagen;
}

public void setImagen(String imagen) {
	this.imagen = imagen;
}

public Image getImagen2() {
	return imagen2;
}

public void setImagen2(Image imagen2) {
	this.imagen2 = imagen2;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}



}
