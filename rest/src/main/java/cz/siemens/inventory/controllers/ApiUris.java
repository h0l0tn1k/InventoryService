package cz.siemens.inventory.controllers;

public abstract class ApiUris {
    public static final String ROOT_URI = "/inventory-service/api/v1";
    public static final String INVENTORY_URI = ROOT_URI + "/inventory-records";
    public static final String CALIBRATIONS_URI = ROOT_URI + "/calibrations";
    public static final String EL_REVISION_URI = ROOT_URI + "/electric-revisions";
    //todo
    public static final String BORROW_URI = ROOT_URI + "/borrow";

    public static final String COMPANY_OWNERS_URI = ROOT_URI + "/company-owners";
    public static final String DEPARTMENT_URI = ROOT_URI + "/departments";
    public static final String DEVICES_URI = ROOT_URI + "/devices";
    public static final String DEVICE_STATES_URI = ROOT_URI + "/device-states";
    public static final String DEVICE_TYPES_URI = ROOT_URI + "/device-types";
    public static final String PROJECTS_URI = ROOT_URI + "/projects";
    public static final String SUPPLIERS_URI = ROOT_URI + "/suppliers";
    public static final String USERS_URI = ROOT_URI + "/users";
}
