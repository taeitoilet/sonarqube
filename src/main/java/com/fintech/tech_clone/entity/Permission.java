package com.fintech.tech_clone.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tblpermission")
public class Permission {
    @Id
    private String permission_name;
    private String description;
    private String permission_creater;
}
