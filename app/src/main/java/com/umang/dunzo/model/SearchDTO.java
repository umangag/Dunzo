package com.umang.dunzo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by umangagarwal on 12,August,2019
 */
public class SearchDTO implements Parcelable {

    public ArrayList<Items> items;


    SearchInformation searchInformation;

    protected SearchDTO(Parcel in) {
        items = in.createTypedArrayList(Items.CREATOR);
        searchInformation = in.readParcelable(SearchInformation.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);
        dest.writeParcelable(searchInformation, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchDTO> CREATOR = new Creator<SearchDTO>() {
        @Override
        public SearchDTO createFromParcel(Parcel in) {
            return new SearchDTO(in);
        }

        @Override
        public SearchDTO[] newArray(int size) {
            return new SearchDTO[size];
        }
    };

    public ArrayList<Items> getItems() {
        return items;
    }


    public SearchInformation getSearchInformation() {
        return searchInformation;
    }

    public static class PageMap implements Parcelable {
        List<Thumbnail> cse_thumbnail;
        List<Details> metatags;

        protected PageMap(Parcel in) {
            cse_thumbnail = in.createTypedArrayList(Thumbnail.CREATOR);
            metatags = in.createTypedArrayList(Details.CREATOR);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeTypedList(cse_thumbnail);
            dest.writeTypedList(metatags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<PageMap> CREATOR = new Creator<PageMap>() {
            @Override
            public PageMap createFromParcel(Parcel in) {
                return new PageMap(in);
            }

            @Override
            public PageMap[] newArray(int size) {
                return new PageMap[size];
            }
        };

        public List<Thumbnail> getCse_thumbnail() {
            return cse_thumbnail;
        }

        public List<Details> getMetatags() {
            return metatags;
        }
    }

    public static class Details implements Parcelable {

        @SerializedName("og:title")
        String title;

        @SerializedName("twitter:title")
        String twitterTitle;

        @SerializedName("og:description")
        String decription;

        @SerializedName("twitter:description")
        String twitterdecription;

        protected Details(Parcel in) {
            title = in.readString();
            twitterTitle = in.readString();
            decription = in.readString();
            twitterdecription = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(twitterTitle);
            dest.writeString(decription);
            dest.writeString(twitterdecription);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Details> CREATOR = new Creator<Details>() {
            @Override
            public Details createFromParcel(Parcel in) {
                return new Details(in);
            }

            @Override
            public Details[] newArray(int size) {
                return new Details[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public String getTwitterTitle() {
            return twitterTitle;
        }

        public String getDecription() {
            return decription;
        }

        public String getTwitterdecription() {
            return twitterdecription;
        }
    }

    public static class Thumbnail implements Parcelable {

        String src;

        protected Thumbnail(Parcel in) {
            src = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(src);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
            @Override
            public Thumbnail createFromParcel(Parcel in) {
                return new Thumbnail(in);
            }

            @Override
            public Thumbnail[] newArray(int size) {
                return new Thumbnail[size];
            }
        };

        public String getSrc() {
            return src;
        }
    }

    public static class Items implements Parcelable {
        String title;
        String link;
        public PageMap pagemap;

        protected Items(Parcel in) {
            title = in.readString();
            link = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(link);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Items> CREATOR = new Creator<Items>() {
            @Override
            public Items createFromParcel(Parcel in) {
                return new Items(in);
            }

            @Override
            public Items[] newArray(int size) {
                return new Items[size];
            }
        };

        public String getTitle() {
            return title;
        }


        public String getLink() {
            return link;
        }


    }

    public static class SearchInformation implements Parcelable {
        String formattedTotalResults;

        protected SearchInformation(Parcel in) {
            formattedTotalResults = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(formattedTotalResults);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<SearchInformation> CREATOR = new Creator<SearchInformation>() {
            @Override
            public SearchInformation createFromParcel(Parcel in) {
                return new SearchInformation(in);
            }

            @Override
            public SearchInformation[] newArray(int size) {
                return new SearchInformation[size];
            }
        };

        public String getFormattedTotalResults() {
            return formattedTotalResults;
        }
    }

}

