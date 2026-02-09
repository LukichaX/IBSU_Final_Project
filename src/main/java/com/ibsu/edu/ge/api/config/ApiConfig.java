package com.ibsu.edu.ge.api.config;

public final class ApiConfig {
    private ApiConfig() {}

    public static final String BASE_URL = "https://automationexercise.com";
    public static final String API_BASE_PATH = "/api";

    public static final String PRODUCTS_LIST = "/productsList";
    public static final String BRANDS_LIST = "/brandsList";
    public static final String SEARCH_PRODUCT = "/searchProduct";
    public static final String VERIFY_LOGIN = "/verifyLogin";
    public static final String CREATE_ACCOUNT = "/createAccount";
    public static final String DELETE_ACCOUNT = "/deleteAccount";
    public static final String UPDATE_ACCOUNT = "/updateAccount"; // თუ დაგჭირდა
    public static final String GET_USER_DETAIL = "/getUserDetailByEmail";
}