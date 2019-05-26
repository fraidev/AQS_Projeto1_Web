package domain.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tb_fiscalizacao")
public class Fiscal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
