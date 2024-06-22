package main.java.com.bestpath.response;

import main.java.com.bestpath.models.Location;

import java.util.List;

public class PathFinderResponse {
    private Double bestTime;
    private List<Location> bestPath;

    public PathFinderResponse(Double bestTime, List<Location> bestPath) {
        this.bestTime = bestTime;
        this.bestPath = bestPath;
    }

    public Double getBestTime() {
        return bestTime;
    }

    public List<Location> getBestPath() {
        return bestPath;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("PathFinderResponse{");
        sb.append("bestTime=").append(bestTime);
        sb.append(", bestPath=").append(bestPath);
        sb.append('}');
        return sb.toString();
    }
}
