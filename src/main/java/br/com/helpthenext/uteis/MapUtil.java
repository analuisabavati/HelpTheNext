package br.com.helpthenext.uteis;

import java.util.*;

public class MapUtil {
    public static <K, V extends Comparable<? super V>> Map<K, V> 
        sortByValue(Map<K, V> map) {
       
    	List<Map.Entry<K, V>> lista = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        
    	Collections.sort( lista, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        });

        Map<K, V> ordenado = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : lista) {
            ordenado.put(entry.getKey(), entry.getValue());
        }
        return ordenado;
    }
    
}