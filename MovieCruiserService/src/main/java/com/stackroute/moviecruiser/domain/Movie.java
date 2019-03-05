package com.stackroute.moviecruiser.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "name")
    private String name;

    @Column(name = "comments")
    private String comments;

    @Column(name = "overview",length = 1000)
    private String overview;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "vote_average")
    private Double vote_average;

    @Column(name = "vote_count")
    private Integer vote_count;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private String userId;

    public Movie(){

    }

    public Movie(Integer id, String name, String comments, String posterPath, String releaseDate, Double voteAverage, Integer voteCount, String userId) {
        this.id = id;
        this.name = name;
        this.comments = comments;
        this.poster_path = posterPath;
        this.release_date = releaseDate;
        this.vote_average = voteAverage;
        this.vote_count = voteCount;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
