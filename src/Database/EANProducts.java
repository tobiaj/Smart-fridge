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
                query = "SELECT product FROM EANProducts product WHERE product.id = :barcode"
        )
})

@Entity(name = "Products")
public class EANProducts implements Serializable {

    private static final long serialVersionUID = 4L;

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;


    public EANProducts() {
    }

    public EANProducts(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "EANProducts{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
