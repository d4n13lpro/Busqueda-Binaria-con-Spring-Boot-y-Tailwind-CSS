package com.daniel.busquedaBinaria.controller;

import com.daniel.busquedaBinaria.service.SearchService;
import com.daniel.busquedaBinaria.service.SearchService.SearchResult; // Importar la clase interna o su propio archivo

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller // Indica que esta clase es un controlador Spring MVC
public class SearchController {

    private final SearchService searchService; // Inyectar el servicio

    // Inyección de dependencias a través del constructor (recomendado)
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/") // Maneja las solicitudes GET a la ruta raíz (cuando se carga la página)
    public String showForm() {
        return "index.html"; // Retorna el nombre de la plantilla a renderizar
    }

    @PostMapping("/") // Maneja las solicitudes POST a la ruta raíz (cuando se envía el formulario)
    public String processForm(
            @RequestParam("array_input") String arrayInputStr,
            @RequestParam("target_input") String targetInputStr,
            Model model) { // El objeto Model se usa para pasar datos a la vista

        try {
            // Convierte el String del array a un array de int
            int[] nums = Arrays.stream(arrayInputStr.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int target = Integer.parseInt(targetInputStr);

            // Delega la ejecución de la búsqueda binaria al servicio
            SearchResult result = searchService.performBinarySearch(nums, target);

            // Añade los resultados al modelo para que la plantilla los pueda usar
            model.addAttribute("result", result.index);
            model.addAttribute("sortedArray", Arrays.toString(result.sortedArray));
            model.addAttribute("target", target);
            model.addAttribute("duration", String.format("%.2f", (double) result.duration));
            model.addAttribute("arrayInput", arrayInputStr); // Para mantener el valor en el input
            model.addAttribute("targetInput", targetInputStr); // Para mantener el valor en el input

        } catch (NumberFormatException e) {
            // Manejo de errores si la entrada no es válida
            model.addAttribute("error", "Por favor, ingresa números válidos para el array (separados por comas) y el objetivo.");
            model.addAttribute("arrayInput", arrayInputStr); // Mantener lo que se intentó ingresar
            model.addAttribute("targetInput", targetInputStr);
        } catch (Exception e) {
            // Manejo de otros posibles errores
            model.addAttribute("error", "Ocurrió un error inesperado: " + e.getMessage());
            model.addAttribute("arrayInput", arrayInputStr);
            model.addAttribute("targetInput", targetInputStr);
        }

        return "index.html"; // Vuelve a la misma plantilla para mostrar los resultados/errores
    }
}