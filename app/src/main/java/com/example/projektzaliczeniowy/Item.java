package com.example.projektzaliczeniowy;

public class Item {
    String dateAdded;
    String dateEnd;
    String task;
    String priority;
    String status;

    public Item(String dateAdded, String dateEnd, String task, String priority, String status) {
        this.dateAdded = dateAdded;
        this.dateEnd = dateEnd;
        this.task = task;
        this.priority = priority;
        this.status = status;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
