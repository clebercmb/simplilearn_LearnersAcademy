package com.example.modules.subject.dto;

public class SubjectSelectedViewDto {
    private int id;
    private String name;
    private String checked;

    public SubjectSelectedViewDto() {
    }

    public SubjectSelectedViewDto(int id, String name, String checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
