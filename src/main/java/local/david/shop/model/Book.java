package local.david.shop.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by [david] on 21.03.17.
 */
@Entity
@Access(AccessType.FIELD)
public class Book {

    @Id
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @org.hibernate.annotations.Parameter(name = "sequence", value = "book_id_seq"))
    @GeneratedValue(generator = "generator")
    private Long id;
    private String name;
    @Column(length = 2048)
    private String description;
    private String author;
    @Column(name = "pulication_date")
    private String pulicationDate;
    private int amount;
    @Version
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPulicationDate() {
        return pulicationDate;
    }

    public void setPulicationDate(String pulicationDate) {
        this.pulicationDate = pulicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", pulicationDate='" + pulicationDate + '\'' +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
