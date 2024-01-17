package com.example.mehndibook.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class HomeModel implements Serializable {

        int code;

        boolean status;

        String message;

        Data data;


        public void setCode(int code) {
            this.code = code;
        }
        public int getCode() {
            return code;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
        public boolean getStatus() {
            return status;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

        public void setData(Data data) {
            this.data = data;
        }
        public Data getData() {
            return data;
        }

        public class Data implements Serializable {

            @SerializedName("image_url")
            String imageUrl;

            @SerializedName("categories")
            List<Categories> categories;

            @SerializedName("trending_design")
            List<TrendingDesign> trendingDesign;

            @SerializedName("new_design")
            List<NewDesign> newDesign;
            public  class Categories {

                public Categories(String id, String title, String banner) {
                    this.id = id;
                    this.title = title;
                    this.banner = banner;
                }

                @SerializedName("id")
                String id;

                @SerializedName("title")
                String title;

                @SerializedName("banner")
                String banner;


                public void setId(String id) {
                    this.id = id;
                }
                public String getId() {
                    return id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
                public String getTitle() {
                    return title;
                }

                public void setBanner(String banner) {
                    this.banner = banner;
                }
                public String getBanner() {
                    return banner;
                }

            }

            public class TrendingDesign implements Serializable {

                @SerializedName("id")
                String id;

                @SerializedName("title")
                String title;

                @SerializedName("feature_photo")
                String featurePhoto;

                @SerializedName("photos")
                List<Photos> photos;

                public void setId(String id) {
                    this.id = id;
                }
                public String getId() {
                    return id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
                public String getTitle() {
                    return title;
                }

                public void setFeaturePhoto(String featurePhoto) {
                    this.featurePhoto = featurePhoto;
                }
                public String getFeaturePhoto() {
                    return featurePhoto;
                }

                public void setPhotos(List<Photos> photos) {
                    this.photos = photos;
                }
                public List<Photos> getPhotos() {
                    return photos;
                }

                public class Photos implements Serializable {

                    @SerializedName("image")
                    String image;

                    @SerializedName("is_primary")
                    String isPrimary;


                    public void setImage(String image) {
                        this.image = image;
                    }
                    public String getImage() {
                        return image;
                    }

                    public void setIsPrimary(String isPrimary) {
                        this.isPrimary = isPrimary;
                    }
                    public String getIsPrimary() {
                        return isPrimary;
                    }

                }

            }


            public class NewDesign implements Serializable {

                @SerializedName("id")
                String id;

                @SerializedName("title")
                String title;

                @SerializedName("feature_photo")
                String featurePhoto;

                @SerializedName("photos")
                List<Photos> photos;


                public void setId(String id) {
                    this.id = id;
                }
                public String getId() {
                    return id;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
                public String getTitle() {
                    return title;
                }

                public void setFeaturePhoto(String featurePhoto) {
                    this.featurePhoto = featurePhoto;
                }
                public String getFeaturePhoto() {
                    return featurePhoto;
                }

                public void setPhotos(List<Photos> photos) {
                    this.photos = photos;
                }
                public List<Photos> getPhotos() {
                    return photos;
                }

                public class Photos implements Serializable{

                    @SerializedName("image")
                    String image;

                    @SerializedName("is_primary")
                    String isPrimary;

                    public void setImage(String image) {
                        this.image = image;
                    }
                    public String getImage() {
                        return image;
                    }

                    public void setIsPrimary(String isPrimary) {
                        this.isPrimary = isPrimary;
                    }
                    public String getIsPrimary() {
                        return isPrimary;
                    }
                }
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
            public String getImageUrl() {
                return imageUrl;
            }

            public void setCategories(List<Categories> categories) {
                this.categories = categories;
            }
            public List<Categories> getCategories() {
                return categories;
            }

            public void setTrendingDesign(List<TrendingDesign> trendingDesign) {
                this.trendingDesign = trendingDesign;
            }
            public List<TrendingDesign> getTrendingDesign() {
                return trendingDesign;
            }

            public void setNewDesign(List<NewDesign> newDesign) {
                this.newDesign = newDesign;
            }
            public List<NewDesign> getNewDesign() {
                return newDesign;
            }

        }
    }
