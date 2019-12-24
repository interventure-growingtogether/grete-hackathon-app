package rs.iv.grete.model;

public class Alert {
    private String id;
    private String creatorId;
    private String creator_name;
    private String title;
    private String description;
    private String tag;
    private Integer priority;

    public Alert() {
    }

    public Alert(String id, String creatorId, String title, String description, String tag, Integer priority) {
        this.id = id;
        this.creatorId = creatorId;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
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

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }
}
