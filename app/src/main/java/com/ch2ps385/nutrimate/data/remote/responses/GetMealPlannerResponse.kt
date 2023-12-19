package com.ch2ps385.nutrimate.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GetMealPlannerResponse(

	@field:SerializedName("nutrition")
	val nutrition: GetNutritionItem,

	@field:SerializedName("data")
	val data: List<GetDataMealPlanner>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("isEmpty")
	val isEmpty: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class GetDataMealPlanner(

	@field:SerializedName("sumber")
	val sumber: String,

	@field:SerializedName("nama_makanan_clean")
	val namaMakananClean: String,

	@field:SerializedName("sayur")
	val sayur: String,

	@field:SerializedName("daging_ayam")
	val dagingAyam: String,

	@field:SerializedName("biji_bijian")
	val bijiBijian: String,

	@field:SerializedName("buah")
	val buah: String,

	@field:SerializedName("daging_kambing")
	val dagingKambing: String,

	@field:SerializedName("beras")
	val beras: String,

	@field:SerializedName("protein")
	val protein: String,

	@field:SerializedName("tepung")
	val tepung: String,

	@field:SerializedName("tipe")
	val tipe: String,

	@field:SerializedName("kedelai")
	val kedelai: String,

	@field:SerializedName("lemak")
	val lemak: String,

	@field:SerializedName("daging_sapi")
	val dagingSapi: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("nama_makanan")
	val namaMakanan: String,

	@field:SerializedName("daging_kerbau")
	val dagingKerbau: String,

	@field:SerializedName("daging_babi")
	val dagingBabi: String,

	@field:SerializedName("food_id")
	val foodId: String,

	@field:SerializedName("kalori")
	val kalori: String,

	@field:SerializedName("umbi_umbian")
	val umbiUmbian: String,

	@field:SerializedName("karbohidrat")
	val karbohidrat: String,

	@field:SerializedName("susu")
	val susu: String,

	@field:SerializedName("jenis_olahan")
	val jenisOlahan: String,

	@field:SerializedName("telur_ayam")
	val telurAyam: String,

	@field:SerializedName("fast_food")
	val fastFood: String,

	@field:SerializedName("ikan")
	val ikan: String
)

data class GetNutritionItem(

	@field:SerializedName("carbs")
	val carbs: Int,

	@field:SerializedName("protein")
	val protein: Int,

	@field:SerializedName("fat")
	val fat: Int,

	@field:SerializedName("calories")
	val calories: Int
)
