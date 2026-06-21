package com.milys.papeleria.controller;

import com.milys.papeleria.model.Producto;
import com.milys.papeleria.model.ProductoForm;
import com.milys.papeleria.repository.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.*;
import java.util.UUID;

@Controller
@RequestMapping
public class ProductoControlador {

        @Autowired
        private ProductoRepositorio repositorio;

        // Listar todos los productos
        @GetMapping
        public String listarProductos(Model modelo) {
            modelo.addAttribute("productos", repositorio.findAll());
            return "Index"; // Llama al archivo lista_productos.html
        }

    @GetMapping("/lista")
    public String listarP(Model modelo) {
        modelo.addAttribute("productos", repositorio.findAll());
        return "lista_productos"; // Llama al archivo lista_productos.html
    }

        // Mostrar formulario de crear
        @GetMapping("/nuevo")
        public String mostrarFormularioNuevo(Model modelo) {
            modelo.addAttribute("producto", new Producto());
            return "formulario_producto";
        }

     /*   // Guardar o actualizar
        @PostMapping("/guardar")
        public String guardarProducto(@ModelAttribute Producto producto, ProductoForm form, @RequestParam("fileinput") MultipartFile file) throws IOException {

            form.setContenido(file);
           if (!form.getContenido().isEmpty()) {
                // Convert the MultipartFile to a byte array
                producto.setContenido(form.getContenido().getBytes());
            }

            repositorio.save(producto);
            return "redirect:/productos";
        }*/






    @Value("${upload.path}")
    private String uploadPath;

    // Procesa el envío del formulario e imagen
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto,
                                  @RequestParam("fileinput") MultipartFile archivo) {

        if (!archivo.isEmpty()) {
            try {
                // Asegurar que el directorio de subidas exista
                Path directorioPath = Paths.get(uploadPath);
                if (!Files.exists(directorioPath)) {
                    Files.createDirectories(directorioPath);
                }

                // Generar un nombre único para la imagen usando UUID
                String nombreImagen = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename();
                Path rutaCompleta = directorioPath.resolve(nombreImagen);

                // Guardar el archivo en el sistema de archivos
                Files.copy(archivo.getInputStream(), rutaCompleta);

                // Asignar el nombre del archivo a la entidad
                producto.setRutaImage(nombreImagen);

            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/productos?error";
            }
        }

        // Guardar la entidad en la base de datos mediante Spring Data JPA
        repositorio.save(producto);

        return "redirect:/lista";
    }










    // Mostrar formulario de editar
        @GetMapping("/editar/{id}")
        public String mostrarFormularioEditar(@PathVariable Long id, Model modelo) {
            modelo.addAttribute("producto", repositorio.findById(id).orElse(null));
            return "formulario_producto";
        }

        // Eliminar producto
        @GetMapping("/eliminar/{id}")
        public String eliminarProducto(@PathVariable Long id) {
            repositorio.deleteById(id);
            return "redirect:/productos";
        }

    }


