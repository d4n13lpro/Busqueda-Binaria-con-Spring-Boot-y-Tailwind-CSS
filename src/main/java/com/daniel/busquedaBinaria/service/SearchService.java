package com.daniel.busquedaBinaria.service;

import org.springframework.stereotype.Service;
import java.util.Arrays;

/**
 * Servicio que encapsula la lógica de negocio para la búsqueda binaria y
 * la medición de rendimiento asociada.
 * Está anotado con @Service para ser detectado y gestionado por el contenedor de Spring,
 * permitiendo su inyección de dependencia en otras capas, como los controladores.
 */
@Service
public class SearchService {

    /**
     * Clase interna estática para encapsular los resultados de una operación de búsqueda.
     * Facilita la devolución de múltiples valores relacionados (índice, array ordenado y duración)
     * de una manera cohesiva.
     *
     * Consideraciones: Si esta estructura de datos va a ser reutilizada extensivamente
     * fuera de este servicio o requiere una validación más compleja, sería beneficioso
     * moverla a su propio archivo dentro de un paquete 'model' o 'dto' (Data Transfer Object).
     */
    public static class SearchResult {
        public final int index;          // El índice donde se encontró el elemento objetivo, o -1 si no se encontró.
        public final int[] sortedArray;  // Una copia del array después de ser ordenado.
        public final long duration;      // La duración de la operación de búsqueda y ordenación en microsegundos.

        /**
         * Constructor para SearchResult.
         * @param index El índice del elemento encontrado.
         * @param sortedArray El array utilizado para la búsqueda, ya ordenado.
         * @param duration La duración de la operación en microsegundos.
         */
        public SearchResult(int index, int[] sortedArray, long duration) {
            this.index = index;
            this.sortedArray = sortedArray;
            this.duration = duration;
        }
    }

    /**
     * Implementa el algoritmo de búsqueda binaria.
     * Este método asume que el array de entrada ya está ordenado.
     * Si el array no está ordenado, los resultados serán incorrectos.
     *
     * @param array El array de enteros donde buscar el objetivo. **Debe estar ordenado.**
     * @param target El valor entero a buscar dentro del array.
     * @return El índice del elemento objetivo si se encuentra; de lo contrario, -1.
     */
    public int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            // Calcula el punto medio para evitar desbordamientos de enteros (left + right) / 2
            int mid = left + (right - left) / 2;

            if (array[mid] == target) {
                return mid; // Elemento encontrado
            } else if (array[mid] < target) {
                left = mid + 1; // El objetivo está en la mitad derecha
            } else {
                right = mid - 1; // El objetivo está en la mitad izquierda
            }
        }
        return -1; // Elemento no encontrado en el array
    }

    /**
     * Ejecuta una búsqueda binaria completa en el array proporcionado,
     * incluyendo la fase de calentamiento de la JVM y la medición de rendimiento.
     *
     * @param nums El array de enteros original proporcionado por el usuario.
     * Este array será clonado y ordenado internamente.
     * @param target El valor entero a buscar.
     * @return Un objeto {@link SearchResult} que contiene el índice, el array ordenado y la duración de la operación.
     */
    public SearchResult performBinarySearch(int[] nums, int target) {
        // --- Fase de Warm-up de la JVM (Just-In-Time Compiler) ---
        // Este bucle ejecuta las operaciones críticas (clonación, ordenación, búsqueda binaria)
        // un número elevado de veces. El propósito es permitir que el JIT Compiler de la JVM
        // identifique y compile a código máquina optimizado los "hot spots" (métodos frecuentemente usados).
        // Esto asegura que la medición de rendimiento subsiguiente refleje el comportamiento
        // de la aplicación en un estado "caliente" y optimizado, no el costo inicial de carga y compilación.
        for(int i = 0; i < 10000; i++) {
            int[] numsCopyWarmup = nums.clone(); // Clona para no modificar el array original
            Arrays.sort(numsCopyWarmup);         // Ordena el array para la búsqueda binaria
            binarySearch(numsCopyWarmup, target); // Ejecuta la búsqueda
        }

        // --- Fase de Medición Real ---
        // Se registra el tiempo con alta precisión antes de la ejecución de la operación principal.
        long startTime = System.nanoTime();

        // Se clona el array de entrada nuevamente para garantizar que la operación
        // se realice sobre un array fresco y no modificado por el warm-up.
        int[] numsCopyActual = nums.clone();

        // La búsqueda binaria requiere un array ordenado. Arrays.sort() utiliza un Dual-Pivot Quicksort
        // para tipos primitivos, que es un algoritmo de ordenación eficiente (O(n log n) en promedio).
        Arrays.sort(numsCopyActual);

        // Se ejecuta el algoritmo de búsqueda binaria en el array ya ordenado.
        int index = binarySearch(numsCopyActual, target);

        // Se calcula la duración total de la operación (ordenación + búsqueda)
        // y se convierte de nanosegundos a microsegundos para una lectura más amigable.
        long duration = (System.nanoTime() - startTime) / 1000; // Duración en microsegundos

        // Se devuelve un objeto SearchResult con todos los datos relevantes.
        return new SearchResult(index, numsCopyActual, duration);
    }
}