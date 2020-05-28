package ro.siit.clothes.model;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@Entity
public class Clothes {
    @Id
    @GeneratedValue
    private Long id;
    @Size(min = 6, message = "Must contains minimum 6 characters")
    @NotEmpty
    private String title;
    public Category category;
    @Size(min = 20, message = "Must contains minimum 20 characters")
    @NotEmpty
    private String description;
    @Min(value=1, message="must be equal or greater than 1")
    private int price;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateCreated;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @UpdateTimestamp
    private LocalDate dateUpdate;
    @Lob
    @NotEmpty
    private byte[] image;
    public Clothes(){

    }

    public Clothes(@Size(min = 6, message = "Must contains minimum 6 characters") @NotEmpty String title, @Size(min = 20, message = "Must contains minimum 20 characters") @NotEmpty String description, @Size(min = 2, message = "Must contains minimum 20 characters") @NotEmpty int price, Category category, LocalDate dateCreated, LocalDate dateUpdate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.dateCreated = dateCreated;
        this.dateUpdate = dateUpdate;
    }
    public String getImage() {
        if (image != null) {
            try {
                return new String(Base64.encodeBase64(image), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setImage(MultipartFile image) {
        try {
            this.image = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(LocalDate dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
