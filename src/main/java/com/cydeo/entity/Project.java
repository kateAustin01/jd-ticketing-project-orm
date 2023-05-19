package com.cydeo.entity;

import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Table
@Entity(name = "projects")
@Where(clause = "is_deleted=false")
public class Project extends BaseEntity{

@Column(unique = true)
    private String projectCode;
    private String projectName;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private  User assignedManager;
    @Column(columnDefinition = "DATE")
    private LocalDate startDate;
    @Column(columnDefinition = "DATE")
    private LocalDate endDate;
    private String projectDetail;
    @Enumerated(EnumType.STRING)
    private Status projectStatus;



}
