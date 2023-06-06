package com.example.morningcity;

import java.util.ArrayList;

class Field{
    public String getId() {
        return this.id; }
    public void setId(String id) {
        this.id = id; }
    String id;
    public String getType() {
        return this.type; }
    public void setType(String type) {
        this.type = type; }
    String type;
}

class Location{
    public String getLat() {
        return this.lat; }
    public void setLat(String lat) {
        this.lat = lat; }
    String lat;
    public String getLon() {
        return this.lon; }
    public void setLon(String lon) {
        this.lon = lon; }
    String lon;
    public String getLocationName() {
        return this.locationName; }
    public void setLocationName(String locationName) {
        this.locationName = locationName; }
    String locationName;
    public String getStationId() {
        return this.stationId; }
    public void setStationId(String stationId) {
        this.stationId = stationId; }
    String stationId;
    public Time getTime() {
        return this.time; }
    public void setTime(Time time) {
        this.time = time; }
    Time time;
    public ArrayList<WeatherElement> getWeatherElement() {
        return this.weatherElement; }
    public void setWeatherElement(ArrayList<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement; }
    ArrayList<WeatherElement> weatherElement;
    public ArrayList<Parameter> getParameter() {
        return this.parameter; }
    public void setParameter(ArrayList<Parameter> parameter) {
        this.parameter = parameter; }
    ArrayList<Parameter> parameter;
}

class Parameter{
    public String getParameterName() {
        return this.parameterName; }
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName; }
    String parameterName;
    public String getParameterValue() {
        return this.parameterValue; }
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue; }
    String parameterValue;
}

class Records{
    public ArrayList<Location> getLocation() {
        return this.location; }
    public void setLocation(ArrayList<Location> location) {
        this.location = location; }
    ArrayList<Location> location;
}

class Result{
    public String getResource_id() {
        return this.resource_id; }
    public void setResource_id(String resource_id) {
        this.resource_id = resource_id; }
    String resource_id;
    public ArrayList<Field> getFields() {
        return this.fields; }
    public void setFields(ArrayList<Field> fields) {
        this.fields = fields; }
    ArrayList<Field> fields;
}

public class WeatherResponse{
    public String getSuccess() {
        return this.success; }
    public void setSuccess(String success) {
        this.success = success; }
    String success;
    public Result getResult() {
        return this.result; }
    public void setResult(Result result) {
        this.result = result; }
    Result result;
    public Records getRecords() {
        return this.records; }
    public void setRecords(Records records) {
        this.records = records; }
    Records records;
}

class Time{
    public String getObsTime() {
        return this.obsTime; }
    public void setObsTime(String obsTime) {
        this.obsTime = obsTime; }
    String obsTime;
}

class WeatherElement{
    public String getElementName() {
        return this.elementName; }
    public void setElementName(String elementName) {
        this.elementName = elementName; }
    String elementName;
    public String getElementValue() {
        return this.elementValue; }
    public void setElementValue(String elementValue) {
        this.elementValue = elementValue; }
    String elementValue;
}
