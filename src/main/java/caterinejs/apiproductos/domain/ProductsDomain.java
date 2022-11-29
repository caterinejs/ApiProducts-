package caterinejs.apiproductos.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "products_data")
public class ProductsDomain {
    @Id
    private String id;
    private String name;
    private Number precio;

    private String ownerName;

    private String ownerEmail;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrecio(Number precio) {
        this.precio = precio;
    }

    public ProductsDomain(){
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public Number getPrecio() {
        return precio;
    }
}
