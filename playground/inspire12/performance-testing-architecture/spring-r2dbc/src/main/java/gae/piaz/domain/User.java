package gae.piaz.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Table("users")
@Getter
@Setter
public class User {

    @Id
    @Column("user_id")
    private Long userId;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}
