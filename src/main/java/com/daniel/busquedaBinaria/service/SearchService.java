package com.daniel.busquedaBinaria.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class SearchService {

    // Clase interna para encapsular resultados de búsqueda
    public static class SearchResult {
        public final int index;          // Índice del elemento encontrado (-1 si no existe)
        public final int[] sortedArray;  // Array ordenado usado en la búsqueda
        public final long duration;      // Tiempo de ejecución en microsegundos

        public SearchResult(int index, int[] sortedArray, long duration) {
            this.index = index;
            this.sortedArray = sortedArray;
            this.duration = duration;
        }
    }

    // Implementación del algoritmo de búsqueda binaria
    public int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // Prevención de overflow

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Ejecuta búsqueda binaria con medición de rendimiento
    public SearchResult performBinarySearch(int[] nums, int target) {
        // Warm-up JVM para mediciones precisas
        for(int i = 0; i < 10000; i++) {
            int[] temp = nums.clone();
            Arrays.sort(temp);
            binarySearch(temp, target);
        }

        // Medición real
        long startTime = System.nanoTime();
        int[] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        int index = binarySearch(numsCopy, target);

        return new SearchResult(index, numsCopy, (System.nanoTime() - startTime) / 1000);
    }
}