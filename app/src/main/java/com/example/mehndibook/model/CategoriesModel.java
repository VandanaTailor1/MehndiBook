package com.example.mehndibook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriesModel implements Serializable {
       private int code;
       private boolean status;
       private String message;
       private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable{
             private String image_url;
             private List<Design> designs;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public List<Design> getDesigns() {
            return designs;
        }

        public void setDesigns(List<Design> designs) {
            this.designs = designs;
        }

        public class Design implements Serializable{
                private String id;
                private String title;
                private String feature_photo;
                private List<Photos> photos;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFeature_photo() {
                return feature_photo;
            }

            public void setFeature_photo(String feature_photo) {
                this.feature_photo = feature_photo;
            }

            public List<Photos> getPhotos() {
                return photos;
            }

            public void setPhotos(List<Photos> photos) {
                this.photos = photos;
            }

            public class Photos implements Serializable{

                    private String image;
                    private String is_primary;

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getIs_primary() {
                    return is_primary;
                }

                public void setIs_primary(String is_primary) {
                    this.is_primary = is_primary;
                }
            }
             }
        }
}
