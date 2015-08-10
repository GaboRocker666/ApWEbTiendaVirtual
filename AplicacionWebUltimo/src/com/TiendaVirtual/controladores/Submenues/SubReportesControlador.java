package com.TiendaVirtual.controladores.Submenues;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Window;

public class SubReportesControlador extends GenericForwardComposer<Component>{
	@Wire
	Button buttonRepCliente,buttonRepGeneral,buttonRepProductos,buttonRepCategorias;
	Center centro= null;
	Window winReportes;
	int roles;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		centro = (Center)winReportes.getAttribute("centro");
		
	}
	
	public void crearMenu(){
		if(roles!=16){
		//	buttonnuevoli.setDisabled(true);
			//buttonedicionli.setDisabled(true);
		}

	}

    
	public void onClick$buttonRepCliente(){
   	 if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   	   	Window win=(Window) Executions.createComponents("Modulo_Reportes/cliente_frecuente.zul", centro, null );
  		win.setTitle("Nueva Categoria");
  		winReportes.detach();
   }             
                
   public void onClick$buttonRepGeneral(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
  		Window win=(Window) Executions.createComponents("Modulo_Reportes/ReporteGeneral.zul", centro, null );
  		win.setTitle("Busqueda Categorias"); 
  		winReportes.detach();
   }  
	
   public void onClick$buttonRepProductos(){
  	if(centro.getFirstChild()!=null){
  	 centro.removeChild(centro.getFirstChild());
  	 }
   		Window win=(Window) Executions.createComponents("Modulo_Reportes/reporte.zul", centro, null );  		
  		win.setTitle("Listar Categorias");
  		winReportes.detach();
   }
   
   public void onClick$buttonRepCategorias(){
	  	if(centro.getFirstChild()!=null){
	  	 centro.removeChild(centro.getFirstChild());
	  	 }
	   		Window win=(Window) Executions.createComponents("Modulo_Reportes/ReportesCategorias.zul", centro, null );  		
	  		win.setTitle("Listar Categorias");
	  		winReportes.detach();
	   }
}
