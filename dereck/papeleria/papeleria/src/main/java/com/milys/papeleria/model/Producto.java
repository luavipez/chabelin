package com.milys.papeleria.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private double precio;
    @Getter @Setter
    private String rutaImage;

    /*@Lob // Marca el campo como Large Object
    @Column(name = "contenido", columnDefinition = "LONGBLOB") // Especifica el tipo exacto
    @Getter @Setter
    private byte[] contenido;

    // Método auxiliar para convertir bytes a Base64
    public String getImagenBase64() {
        return java.util.Base64.getEncoder().encodeToString(this.contenido);
    }
*/


    // Constructores, Getters y Setters
    public Producto() {}

   /* public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }*/
}
