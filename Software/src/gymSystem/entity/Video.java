package gymSystem.entity;
/**
 * This is the entity class for videos.
 * @version 1.0.0
 * @author Linfei Huang BUPT&QMUL year3 student
 * @since 5/31/2021
 */
public class Video {
    private String videoID;
    private String level;
    private String type;
    private String videoLink;
    private String status;
    private String videoDescription;

    public Video() {

    }

    public Video(String videoID) {
        this.videoID = videoID;
    }

    public Video(String videoID, String level, String type, String videoLink, String status, String videoDescription) {
        this.videoID = videoID;
        this.level = level;
        this.type = type;
        this.videoLink = videoLink;
        this.status = status;
        this.videoDescription = videoDescription;
    }

    public void setVideoID(String videoID) {this.videoID = videoID;}
    public String getVideoID() {return this.videoID;}
    public void setLevel(String level) {this.level = level;}
    public String getLevel() {return this.level;}
    public void setType(String type) {this.type = type;}
    public String getType() {return this.type;}
    public void setVideoLink(String videoLink) {this.videoLink = videoLink;}
    public String getVideoLink() {return this.videoLink;}
    public void setStatus(String status) {this.status = status;}
    public String getStatus() {return this.status;}
    public void setVideoDescription(String videoDescription) {this.videoDescription = videoDescription;}
    public String getVideoDescription() {return this.videoDescription;}
}
