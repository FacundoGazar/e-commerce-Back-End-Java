package com.techlab.util;

public class FormateoDeTexto {
	public static String formatearNombreProducto( String nombre){
        nombre=nombre.trim().toLowerCase();

        String[] palabras=nombre.split(" ");

        StringBuilder sb= new StringBuilder();

        for(int i=0;i<palabras.length;i++){
            if(!palabras[i].isEmpty()){

                String primerLetra=palabras[i].substring(0,1).toUpperCase();

                String resto=palabras[i].substring(1);

                sb.append(primerLetra).append(resto);

                if(i< palabras.length-1){
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }
}
