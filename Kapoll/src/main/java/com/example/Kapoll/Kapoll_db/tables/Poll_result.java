package com.example.Kapoll.Kapoll_db.tables;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Poll_result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date utilDate;

    private int noVote;
    private int yesVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pollid")
    private Poll pollid;



    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}



    public int getNoVote() {
        return noVote;
    }

    public void setNoVote(int noVote) {
        this.noVote = noVote;
    }

    public int getYesVote() {
        return yesVote;
    }

    public void setYesVote(int yesVote) {
        this.yesVote = yesVote;
    }

    public Date getUtilDate() {
        return utilDate;
    }

    public void setUtilDate(Date utilDate) {
        this.utilDate = utilDate;
    }
}
