package com.randy.toDoList.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;





@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message= "First Name is required")
    private String firstName;
    
    
    private String lastName;
    
    @Email(message="Email must be valid")
    @NotEmpty(message= "Email is required")
    private String email;
    
    
    @Size(min=5, message="Password must be greater than 5 characters")
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    
    private Date updatedAt;
    
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
    private List<Ideas> ideasCreated;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_ideas", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "ideas_id")
    )
    private List<Ideas> ideasBelongTo;
    
    
    public User() {
    }

    
    
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }



	public List<Ideas> getIdeasCreated() {
		return ideasCreated;
	}



	public void setIdeasCreated(List<Ideas> ideasCreated) {
		this.ideasCreated = ideasCreated;
	}



	public List<Ideas> getIdeasBelongTo() {
		return ideasBelongTo;
	}



	public void setIdeasBelongTo(List<Ideas> ideasBelongTo) {
		this.ideasBelongTo = ideasBelongTo;
	}
    
    
}
    
    
