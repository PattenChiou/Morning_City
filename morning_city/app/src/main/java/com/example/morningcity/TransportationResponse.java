package com.example.morningcity;

import java.util.List;

public class TransportationResponse {
    private List<Data> Data;

    public List<Data> getData() {
        return Data;
    }

    public void setData(List<Data> data) {
        this.Data = data;
    }

    public static class Data {
        private String StopUID;
        private String StopID;
        private StopName StopName;
        private String RouteUID;
        private String RouteID;
        private RouteName RouteName;
        private int Direction;
        private int EstimateTime;
        private int StopStatus;
        private String SrcUpdateTime;
        private String UpdateTime;

        public String getStopUID() {
            return StopUID;
        }

        public void setStopUID(String stopUID) {
            this.StopUID = stopUID;
        }

        public String getStopID() {
            return StopID;
        }

        public void setStopID(String stopID) {
            this.StopID = stopID;
        }

        public StopName getStopName() {
            return StopName;
        }

        public void setStopName(StopName stopName) {
            this.StopName = stopName;
        }

        public String getRouteUID() {
            return RouteUID;
        }

        public void setRouteUID(String routeUID) {
            this.RouteUID = routeUID;
        }

        public String getRouteID() {
            return RouteID;
        }

        public void setRouteID(String routeID) {
            this.RouteID = routeID;
        }

        public RouteName getRouteName() {
            return RouteName;
        }

        public void setRouteName(RouteName routeName) {
            this.RouteName = routeName;
        }

        public int getDirection() {
            return Direction;
        }

        public void setDirection(int direction) {
            this.Direction = direction;
        }

        public int getEstimateTime() {
            return EstimateTime;
        }

        public void setEstimateTime(int estimateTime) {
            this.EstimateTime = estimateTime;
        }

        public int getStopStatus() {
            return StopStatus;
        }

        public void setStopStatus(int stopStatus) {
            this.StopStatus = stopStatus;
        }

        public String getSrcUpdateTime() {
            return SrcUpdateTime;
        }

        public void setSrcUpdateTime(String srcUpdateTime) {
            this.SrcUpdateTime = srcUpdateTime;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.UpdateTime = updateTime;
        }
    }

    public static class StopName {
        private String Zh_tw;
        private String En;

        public String getZh_tw() {
            return Zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.Zh_tw = zh_tw;
        }

        public String getEn() {
            return En;
        }

        public void setEn(String en) {
            this.En = en;
        }
    }

    public static class RouteName {
        private String Zh_tw;
        private String En;

        public String getZh_tw() {
            return Zh_tw;
        }

        public void setZh_tw(String zh_tw) {
            this.Zh_tw = zh_tw;
        }

        public String getEn() {
            return En;
        }

        public void setEn(String en) {
            this.En = en;
        }
    }
}
