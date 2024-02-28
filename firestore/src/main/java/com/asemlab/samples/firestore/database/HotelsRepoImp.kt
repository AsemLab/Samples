package com.asemlab.samples.firestore.database

import com.asemlab.samples.firestore.model.Filter
import com.asemlab.samples.firestore.model.Hotel
import com.asemlab.samples.firestore.model.Rate
import com.asemlab.samples.firestore.model.SortBy
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class HotelsRepoImp(private val db: FirebaseFirestore) : HotelsRepository {

    // TODO Get list of entities from Firestore
    override suspend fun getHotels(onSuccess: (List<Hotel>) -> Unit) {

        db.collection("hotels").addSnapshotListener { value, error ->
            onSuccess(value?.documents?.map { h ->
                h.data!!.toHotel()
            } ?: emptyList())
        }
    }

    // TODO Insert or update in Firestore
    override suspend fun insertHotel(
        hotel: Hotel,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("hotels").document(hotel.id).set(hotel).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure(it.message ?: "")
        }
    }

    // TODO Delete from Firestore
    override suspend fun deleteHotel(
        hotel: Hotel,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("hotels").document(hotel.id).delete().addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            onFailure(it.message ?: "")
        }
    }

    // TODO Update field in entity in Firestore
    override suspend fun insertRate(
        hotel: Hotel,
        rate: Rate,
        onSuccess: (List<Rate>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection("hotels").document(hotel.id).update("rates", FieldValue.arrayUnion(rate))
            .addOnSuccessListener {
                db.collection("hotels").document(hotel.id).get().addOnSuccessListener {
                    onSuccess(it.data?.toHotel()?.rates ?: emptyList())
                }
            }.addOnFailureListener {
                onFailure(it.message ?: "Error when insert new rate")
            }
    }

    // TODO Get sorted list from Firestore
    override suspend fun sortHotel(sortBy: SortBy, onSuccess: (List<Hotel>) -> Unit) {

        db.collection("hotels").orderBy(sortBy.title.lowercase())
            .addSnapshotListener { value, error ->
                onSuccess(value?.documents?.map { h ->
                    h.data!!.toHotel()
                } ?: emptyList())
            }
    }

    // TODO Get filtered list from Firestore
    override suspend fun filterHotel(filter: Filter, value: Any, onSuccess: (List<Hotel>) -> Unit) {

        db.collection("hotels").whereEqualTo(filter.title, value)
            .addSnapshotListener { value, error ->
                onSuccess(value?.documents?.map { h ->
                    h.data!!.toHotel()
                } ?: emptyList())
            }
    }

    // Utility function to transform from Firestore structure
    private fun Map<String, Any>.toHotel(): Hotel {
        val hotel = Hotel(
            this["name"].toString(),
            this["city"].toString(),
            (this["stars"] as Long).toInt(),
            this["freeWifi"] as Boolean,
            this["swimmingPool"] as Boolean
        ).also {
            it.id = this["id"].toString()
            it.rates = (this["rates"] as ArrayList<HashMap<String, *>>?)?.map {
                Rate(
                    (it["score"] as Long).toInt(),
                    it["comment"].toString(),
                    it["username"].toString()
                )
            }?.toList()
        }
        return hotel
    }
}