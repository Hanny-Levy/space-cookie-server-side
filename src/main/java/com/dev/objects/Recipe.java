package com.dev.objects;

import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "recipes")
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String title ;
    @Column
    private String imgLink;
    @Column
    private String link ;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Recipe(String title, String imgLink, String link, User user) {
        this.title = title;
        this.imgLink = imgLink;
        this.link = link;
        this.user = user;
    }
    public Recipe() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }
}
