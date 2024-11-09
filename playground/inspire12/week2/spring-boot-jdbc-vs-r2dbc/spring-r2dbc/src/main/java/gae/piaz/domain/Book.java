package gae.piaz.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("books")
@Getter
@Setter
public class Book {

	@Id
	@Column("book_id")
	// spring data r2dbc does not support sequences:
	// https://github.com/spring-projects/spring-data-r2dbc/issues/370
	private Long bookId;

	@Column("author")
	private String author;

	@Column("isbn")
	private String isbn;

	@Column("title")
	private String title;

	@Column("year")
	private Integer year;

}
