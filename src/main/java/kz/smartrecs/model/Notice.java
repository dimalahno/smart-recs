package kz.smartrecs.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(schema = "public", name = "notice_details")
@Data
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Integer noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Column(name = "notic_beg_dt")
    private Date noticBegDt;

    @Column(name = "notic_end_dt")
    private Date noticEndDt;

    @Column(name = "create_dt")
    private Date createDt;

    @Column(name = "update_dt")
    private Date updateDt;
}
