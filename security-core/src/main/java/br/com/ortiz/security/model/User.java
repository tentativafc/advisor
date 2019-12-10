package br.com.ortiz.security.model;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Data
@Table
public class User implements Serializable {
    @PrimaryKey
    private UUID id;
    private String email;
    @Column("google_user_id")
    private String googleUserId;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
    @Column("password")
    private String password;

}
