package br.com.helpthenext.util;

import java.util.*;

public class HashMapUtil {
    public static <K, V extends Comparable<? super V>> HashMap<K, V> 
        sortByValue(HashMap<K, V> map) {
       
    	List<HashMap.Entry<K, V>> lista = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        
    	Collections.sort(lista, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        });
    	
    	Collections.reverse(lista);

    	HashMap<K, V> ordenado = new LinkedHashMap<K, V>();
        for (HashMap.Entry<K, V> entry : lista) {
            ordenado.put(entry.getKey(), entry.getValue());
        }
        return ordenado;
    }
    
}