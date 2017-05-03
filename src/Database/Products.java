package Database;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by tobiaj on 2017-04-26.
 */

    @NamedQueries({
            @NamedQuery(
                    name = "get",
                    query = "SELECT product FROM Products product WHERE product.id = :barcode"
            )
    })

    @Entity(name = "Products")
    public class Products implements Serializable {

        private static final long serialVersionUID = 4L;

        @Id
        @Column(name = "id", nullable = false)
        private int id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "quantity", nullable = false)
        private int quantity;

        @Column(name = "created_at", nullable = false)
        private Timestamp created_at;

        @Column(name = "updated_at", nullable = false)
        private Timestamp updated_at;

    public Products() {
    }

    public Products(int id, String name, int quantity, Timestamp created_at, Timestamp updated_at) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public static long getSerialVersionUID() {
            return serialVersionUID;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
