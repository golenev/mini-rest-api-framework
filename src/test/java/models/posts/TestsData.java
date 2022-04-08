package models.posts;

import lombok.Data;

@Data
public class TestsData {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;

    @Override
    public String toString() {
        return "TestsData{" +
                "duration='" + duration + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
