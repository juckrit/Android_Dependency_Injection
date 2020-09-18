package com.example.mytestapp.Activity.Model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResultModel(
        @SerializedName("summaries") @Expose val summaries: List<BigSummaryModel?>?,
        @SerializedName("has_next_page") @Expose val has_next_page: Boolean?
)

@Entity(tableName = "bigSummaryModel_table")
data class BigSummaryModel(
        @PrimaryKey @NonNull @SerializedName("id") @Expose val id: Int = 0,
        @SerializedName("category_id") @Expose val category_id: Int= 0,
        @SerializedName("title") @Expose val title: String= "",
        @SerializedName("category_name") @Expose val category_name: String= "",
        @SerializedName("candy") @Expose val candy: Int= 0,
        @SerializedName("curator_name") @Expose val curator_name: String= "",
        @SerializedName("icon_url") @Expose val icon_url: String= "",
        @SerializedName("image_url") @Expose val image_url: String= "",
        @SerializedName("video_url") @Expose val video_url: String= "",
        @SerializedName("tag_name") @Expose val tag_name: String?= "",
        @SerializedName("view") @Expose val view: Int= 0,
        @SerializedName("like") @Expose val like: Int= 0
)

//data class SummaryWIthTotalModel(
//        @SerializedName("id") @Expose val id: Int?,
//        @SerializedName("title") @Expose val title: String?,
//        @SerializedName("category_name") @Expose val category_name: String?,
//        @SerializedName("curator_name") @Expose val curator_name: String?,
//        @SerializedName("image_url") @Expose val image_url: String?,
//        @SerializedName("view") @Expose val view: Int?,
//        @SerializedName("like") @Expose val like: Int?,
//        val total: Int?
//)