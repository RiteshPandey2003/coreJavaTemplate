package model;

public class noteModel {
    private int task_id;
    private String description;
    private String status;
    private String links;

    public noteModel(int task_id, String description, String status, String links) {
        this.task_id = task_id;
        this.description = description;
        this.status = status;
        this.links = links;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
