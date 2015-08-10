package com.TiendaVirtual.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Productos;
import com.TiendaVirtual.modelos.DBCategorias;
import com.TiendaVirtual.modelos.DBProductos;

public class ProductosControlador extends GenericForwardComposer<Component>{
//enlace a los componentes de la interfaz
	@Wire
	Combobox cmb_Categoria;
	Textbox  textbox_Nombre_Producto, textbox_descripcion, textbox_Stock_Minimo, textbox_Stock_Actual, textbox_Precio;
	Button button_Registrar,buttonSeleccionar,button_Cancelar;
	Window winNuevoProducto;
	Image img_Producto;
	Vlayout pics;
	Row rwSA,rwPr,rwSM;
	
	String ruta="";
	
	public void onClose$winNuevoProducto(){
		if(ruta.isEmpty()){
			
		}else{
			File file=new File(ruta);
			file.delete();
		}
	}
	
	public void onClick$button_Cancelar(){
		if(ruta.isEmpty()){
			
		}else{
			File file=new File(ruta);
			file.delete();
		}
		winNuevoProducto.detach();
		
		
	}
	
	public void onUpload$buttonSeleccionar(UploadEvent event){
		Image image = new Image();
		FileOutputStream fos=null;
		try {
			pics.removeChild(pics.getChildren().get(0));
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		Media media =  event.getMedia();
		if (media instanceof org.zkoss.image.Image) {
			image.setContent((org.zkoss.image.Image) media);
			image.setParent(pics);
			try {
				ruta=media.getName();
				fos=new FileOutputStream(media.getName());
				byte codigos[]=media.getByteData();
				fos.write(codigos);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("erorr: "+e.getMessage());
			}
			
			
		} else {
			Messagebox.show("Not an image: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
		}	
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
				DBCategorias dbcategorias = new DBCategorias();
				ArrayList<Categorias> lista = dbcategorias.buscarcategorias("");
				ListModelList<Categorias> modeloDeDatos= new ListModelList<Categorias>(lista);
				cmb_Categoria.setModel(modeloDeDatos);
				}

	public void onCreate$winNuevoProducto(){
		Productos productos = (Productos) winNuevoProducto.getAttribute("productos");
		String op="";
		op=(String)winNuevoProducto.getAttribute("op");
		if(productos != null){
			cmb_Categoria.setText(productos.getCategoria().getNom_categoria());
			textbox_Nombre_Producto.setText(productos.getNombre_producto());
			textbox_descripcion.setText(productos.getDescripcion());
			textbox_Stock_Minimo.setText(String.valueOf(productos.getStock_minimo()));
			textbox_Stock_Actual.setText(String.valueOf(productos.getStock_actual()));
			textbox_Precio.setText(""+productos.getPrecio());
			Image image = new Image();
			if(productos.getImagen2()!=null){
				if (productos.getImagen2() instanceof BufferedImage)
			    {
					image.setContent((BufferedImage) productos.getImagen2());
			    }else{
			    	BufferedImage bimage = new BufferedImage(productos.getImagen2().getWidth(null), productos.getImagen2().getHeight(null), BufferedImage.TYPE_INT_ARGB);
			    	image.setContent(bimage);
			    	
			    }
			    image.setParent(pics);
			}
	
		}
		System.out.println(op);
		try {
			if(op.equals("1")){
				winNuevoProducto.setWidth("100%");
				winNuevoProducto.setHeight("100%");
			}
		} catch (Exception e) {
			winNuevoProducto.setWidth("80%");
			winNuevoProducto.setHeight("93%");
			// TODO: handle exception
		}
		
			
		}
		
	public void onCLick$buttonSeleccionar(){
		
	}
	
	public void onClick$button_Registrar(){
		Productos productos = (Productos) winNuevoProducto.getAttribute("productos");		
		if(productos !=null ){
			guardar(productos,1);			
		}else{
			productos = new Productos();
			Categorias categorias = new Categorias();
			productos.setCategoria(categorias);
			guardar(productos,2);		
		}
	}
	
		
	public void guardar(Productos productos, int opcion){
		DBProductos dbproductos = new DBProductos();
		DBCategorias dbcategorias = new DBCategorias();
		
		
		//buscamos el id del valor obtenido del combo y lo guardamos en una variable
		ArrayList<Categorias> lista= null;
		lista= new ArrayList<Categorias>();
		lista = dbcategorias.buscarcategorias(cmb_Categoria.getText());
		Categorias categorias = productos.getCategoria();
		categorias.setId(lista.get(0).getId());
		//alert("este es el valor: "+ lista.get(0).getId());
		
		
		
		productos.setNombre_producto(textbox_Nombre_Producto.getText());
		productos.setDescripcion(textbox_descripcion.getText());
		productos.setStock_minimo(Integer.valueOf(textbox_Stock_Minimo.getText()));
		productos.setStock_actual(Integer.valueOf(textbox_Stock_Actual.getText()));
		productos.setPrecio(Double.parseDouble(textbox_Precio.getText()));
		productos.setImagen(ruta);
		
		
		boolean resultado= false;		
		if(opcion == 1){
			//Editar
			resultado= dbproductos.editarproductos(productos);			
			
		}else if(opcion==2){
			//Nuevo
			resultado= dbproductos.crearproductos(productos);
		}
		
		if(resultado){
			alert("Guardado Exitosamente");
			winNuevoProducto.detach();
		}else{
			alert("Error al guardar producto");
		}
		
	}
	
}
