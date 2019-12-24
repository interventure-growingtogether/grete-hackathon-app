package rs.iv.grete.model;

public class Alert {
    private String id;
    private String userId;
    private String title;
    private String description;
    private String tag;
    private Integer priority;

    public Alert() {
    }

    public Alert(String id, String userId, String title, String description, String tag, Integer priority) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
