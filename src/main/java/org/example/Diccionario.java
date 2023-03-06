package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Diccionario {
    private HashMap<Character, LinkedList<String>> tablaHash;

    public Diccionario() {
        tablaHash = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            tablaHash.put(c, new LinkedList<String>());
        }
    }

    public void agregarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> palabrasConLetra = tablaHash.get(primeraLetra);
        if (palabrasConLetra == null) {
            palabrasConLetra = new LinkedList<String>();
            tablaHash.put(primeraLetra, palabrasConLetra);
        }
        palabrasConLetra.add(palabra);
    }

    public boolean buscarPalabra(String palabra) {
        char primeraLetra = palabra.charAt(0);
        LinkedList<String> palabrasConLetra = tablaHash.get(primeraLetra);
        return palabrasConLetra.contains(palabra);
    }


    public void leerDiccionario(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            for (int i = 0; i < 4; i++) {
                br.readLine();
            }

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String palabra = datos[0];
                agregarPalabra(palabra);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void imprimirTablaHash() {
        for (HashMap.Entry<Character, LinkedList<String>> entry : tablaHash.entrySet()) {
            char letra = entry.getKey();
            LinkedList<String> palabrasConLetra = entry.getValue();
            System.out.print(letra + ": ");
            if (palabrasConLetra != null) {
                for (String palabra : palabrasConLetra) {
                    System.out.print(palabra + " ");
                }
            }
            System.out.println();
        }
    }

    public void lanzador() {
        Diccionario diccionario = new Diccionario();
        diccionario.leerDiccionario("palabras.csv");
        diccionario.imprimirTablaHash();
    }
}
//No se puede subir el csv