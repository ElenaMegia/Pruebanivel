package org.example;

import java.util.LinkedList;

public class TablaHash<T> {
    private T[] tabla;
    private int tamaño;
    private int cantidad;

    public TablaHash(int tamaño) {
        this.tamaño = tamaño;
        tabla = (T[]) new Object[tamaño];
        cantidad = 0;
    }

    public void agregar(T dato) {
        //creame una variable con el tamaño de la tabla
        int tamaño_tabla = tabla.length;

        int posicion = funcion_hash(dato, tamaño_tabla);
        if (tabla[posicion] != null) {
            posicion = sondeo(posicion, tamaño_tabla);
        }
        tabla[posicion] = dato;
        cantidad++;
    }

    public void quitar(T dato) {
        int posicion = buscar(dato);
        if (posicion != -1) {
            tabla[posicion] = null;
            cantidad--;
        }
    }

    public int buscar(T dato) {
        //creame una variable con el tamaño de la tabla
        int tamaño_tabla = tabla.length;
        int posicion = funcion_hash(dato, tamaño_tabla);
        while (tabla[posicion] != null && !tabla[posicion].equals(dato)) {
            posicion = sondeo(posicion, tamaño_tabla);
        }
        if (tabla[posicion] != null && tabla[posicion].equals(dato)) {
            return posicion;
        } else {
            return -1;
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public T[] getTabla() {
        return tabla;
    }

    public  int funcion_hash(Object dato, int tamaño_tabla) {
        int hash = dato.hashCode();
        int posicion = hash % tamaño_tabla;
        return (posicion < 0) ? -posicion : posicion; // para evitar posiciones negativas
    }

    public  int sondeo(int posicion, int tamaño_tabla) {
        return (posicion + 1) % tamaño_tabla; // implementación básica de sondeo lineal
    }

    public  int cantidad_elementos(Object[] tabla) {
        int count = 0;
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != null) {
                count++;
            }
        }
        return count;
    }
}