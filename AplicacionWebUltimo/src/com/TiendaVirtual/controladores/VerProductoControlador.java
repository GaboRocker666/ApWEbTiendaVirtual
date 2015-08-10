package com.TiendaVirtual.controladores;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

import com.TiendaVirtual.entidades.Categorias;
import com.TiendaVirtual.entidades.Productos;
import com.TiendaVirtual.modelos.DBCategorias;

public class VerProductoControlador extends GenericForwardComposer<Component>{
	@Wire
	Textbox  txt_Categoria,textbox_Nombre_Producto, textbox_descripcion, textbox_Stock_Minimo, textbox_Stock_Actual, textbox_Precio;
	Window winVerProducto;
	Image img_Producto;
	Vlayout pics;
	Row rwSA,rwPr,rwSM;
	
	String ruta="";
	
	public void onClose$winVerProducto(){
		if(ruta.isEmpty()){
			
		}else{
			File file=new File(ruta);
			file.delete();
		}
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
	}
	
	public void onCreate$winVerProducto(){
		Productos productos = (Productos) winVerProducto.getAttribute("productos");
		if(productos != null){
			txt_Categoria.setText(productos.getCategoria().getNom_categoria());
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
				textbox_Nombre_Producto.setReadonly(true);
				textbox_descripcion.setReadonly(true);
				txt_Categoria.setReadonly(true);
				textbox_Precio.setReadonly(true);
				textbox_Stock_Actual.setReadonly(true);
				textbox_Stock_Minimo.setReadonly(true);
		}
	}
}
