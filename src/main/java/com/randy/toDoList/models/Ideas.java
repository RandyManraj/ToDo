package com.randy.toDoList.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="ideas")
public class Ideas {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message= "Idea is required")
    private String ideaTitle;
	private String description;
	private String likes;
	private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_ideas", 
        joinColumns = @JoinColumn(name = "ideas_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likedUsers;
    
    
    public Ideas() {}
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdeaTitle() {
		return ideaTitle;
	}

	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}

	

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(List<User> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
    
}
