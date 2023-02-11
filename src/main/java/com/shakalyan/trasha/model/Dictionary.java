package com.shakalyan.trasha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="dictionaries")
public class Dictionary {

    @Id
    private Integer id;

    private String name;

    private Integer userId;

}
